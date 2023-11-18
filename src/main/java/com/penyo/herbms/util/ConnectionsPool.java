package com.penyo.herbms.util;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 连接池。
 * <p>
 * 连接池是一种对 {@link java.sql.Connection Connection} 池化技术的体现。其生命周期分为<b>初始期、
 * 伺服期和终结期</b>。首次调用类方法时立刻进入初始期，期间类拒绝响应；当驱动和连接集合就绪后，进入伺服期，
 * 可以向外提供有关 {@link com.penyo.herbms.util.ConnectionShell ConnectionShell} 的服务；
 * 手动调用 {@code shutdown()} 方法后，连接池进入终结期，其内的资源会被全部销毁。
 *
 * @author Penyo
 */
public class ConnectionsPool {
  /**
   * 公共驱动
   */
  private static final String driver;
  /**
   * 公共地址
   */
  private static final String url;
  /**
   * 公共账户的用户名
   */
  private static final String username;
  /**
   * 公共账户的密码
   */
  private static final String password;
  /**
   * 是否为开发环境
   */
  private static final boolean isDev;

  static {
    Properties properties = new Properties();
    InputStream inputStream = ConnectionsPool.class.getClassLoader().getResourceAsStream("db.properties");
    try {
      properties.load(inputStream);
      driver = properties.getProperty("driverClass");
      url = properties.getProperty("url");
      username = properties.getProperty("username");
      password = properties.getProperty("password");
      isDev = properties.getProperty("env").equals("dev");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static {
    try {
      Class.forName(driver);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 空闲队列
   */
  private static final LinkedList<ConnectionShell> remainings = new LinkedList<>();
  /**
   * 忙碌队列
   */
  private static final LinkedList<ConnectionShell> workings = new LinkedList<>();
  /**
   * 池深度
   */
  private static final int POOL_SIZE = 64;

  static {
    for (int i = 0; i < POOL_SIZE; i++)
      try {
        remainings.add(new ConnectionShell(DriverManager.getConnection(url, username, password)));
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  /**
   * 获取连接壳。
   */
  public static synchronized ConnectionShell getShell() {
    ConnectionShell cs = null;

    long requestTime = System.currentTimeMillis();
    long timeout = 5000;

    while (cs == null && System.currentTimeMillis() - requestTime < timeout) {
      if (remainings.size() == 0) try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      }
      else {
        cs = remainings.poll();
        cs.enable();
        workings.offer(cs);
        ConnectionsPool.log("发生了一次借出");
      }
    }

    return cs;
  }

  /**
   * 归还连接壳。
   */
  public static synchronized void returnShell(ConnectionShell cs) {
    if (workings.contains(cs)) {
      workings.remove(cs);
      cs.disable();
      remainings.offer(cs);
      ConnectionsPool.log("发生了一次归还");
    }
  }

  /**
   * 日志输出。
   */
  public static void log(String eventMsg) {
    if (isDev)
      System.out.println(Instant.now() + " " + eventMsg + " " + "当前可用连接量" + remainings.size() + "/" + POOL_SIZE);
  }

  /**
   * 尝试关闭临时对象。
   */
  public static void close(PreparedStatement ps, ResultSet rs) {
    if (rs != null) try {
      rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ps != null) try {
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 关闭连接池。
   */
  public static void shutdown() {
    for (ConnectionShell cs : workings)
      try {
        ConnectionsPool.returnShell(cs);
        cs.getUsufruct().close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    for (ConnectionShell cs : remainings)
      try {
        cs.getUsufruct().close();
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
}
