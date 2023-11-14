package com.penyo.herbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.util.DatabaseUtil;

/**
 * 持久层。
 */
public abstract class DAO<T> {
  private Connection c;
  private PreparedStatement ps = null;
  private ResultSet rs = null;

  public DAO() {
    this.c = DatabaseUtil.getConnection();
  }

  /**
   * 运行指定 SQL 语句并使用指定参数列填充。目的主要在于设置。
   */
  public int runRawSQLToUpdate(String sql, Object... params) {
    int num = -1;
    try {
      ps = c.prepareStatement(sql);
      for (int i = 0; i < params.length; i++)
        ps.setObject(i + 1, params[i]);
      num = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseUtil.close(ps, rs);
    }
    return num;
  }

  /**
   * 运行指定 SQL 语句并使用指定参数列填充。目的主要在于获取。
   */
  public List<T> runRawSQLToQuery(RowMapper<T> rm, String sql, Object... params) {
    List<T> list = new ArrayList<T>();
    try {
      ps = c.prepareStatement(sql);
      for (int i = 0; i < params.length; i++)
        ps.setObject(i + 1, params[i]);
      rs = ps.executeQuery();
      while (rs.next())
        list.add(rm.mapRow(rs));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseUtil.close(ps, rs);
    }
    return list;
  }

  /**
   * 关闭连接。
   */
  public void close() {
    DatabaseUtil.close(c);
  }

  /**
   * 添加单个元素。
   */
  public abstract int add(T o);

  /**
   * 删除单个元素。
   */
  public abstract int delete(int id);

  /**
   * 查找单个元素。
   */
  public abstract T select(int id);

  /**
   * 查找全部元素。
   */
  public abstract List<T> selectAll();

  /**
   * 修改单个元素。
   */
  public abstract int update(T o);
}
