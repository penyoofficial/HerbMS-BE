package com.penyo.herbms.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.penyo.tsington.cfg.UserConfig;
import com.penyo.tsington.util.SQLDBProduct;
import com.penyo.tsington.v0.TsingtonDataSource;

/**
 * 数据库快捷工具包
 *
 * @author Penyo
 */
public class DBUtil {
  /**
   * 公共连接池
   */
  public static final TsingtonDataSource pool = new TsingtonDataSource(SQLDBProduct.MYSQL, new UserConfig(DBUtil.class.getClassLoader().getResourceAsStream("user.properties")));

  public static void close(PreparedStatement ps, ResultSet rs) {
    if (rs != null) try {
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (ps != null) try {
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
