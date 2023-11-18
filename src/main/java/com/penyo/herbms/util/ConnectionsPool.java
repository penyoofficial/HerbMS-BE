package com.penyo.herbms.util;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 连接池。
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

  static {
    Properties properties = new Properties();
    InputStream inputStream = ConnectionsPool.class.getClassLoader().getResourceAsStream("db.properties");
    try {
      properties.load(inputStream);
      driver = properties.getProperty("driverClass");
      url = properties.getProperty("url");
      username = properties.getProperty("username");
      password = properties.getProperty("password");
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

  static {
    final int POOL_SIZE = 64;
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
    System.out.println(remainings.size());

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
    }
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

  @Override
  protected void finalize() {
    for (ConnectionShell cs : workings)
      try {
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
