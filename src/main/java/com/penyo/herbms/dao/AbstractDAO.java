package com.penyo.herbms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.util.ConnectionShell;
import com.penyo.herbms.util.ConnectionsPool;

/**
 * 数据访问代理。
 *
 * @author Penyo
 */
public abstract class AbstractDAO<T> {
  /**
   * 连接壳
   */
  private final ConnectionShell cs;
  /**
   * 命令缓存
   */
  private PreparedStatement ps = null;
  /**
   * 结果缓存
   */
  private ResultSet rs = null;

  protected AbstractDAO() {
    this.cs = ConnectionsPool.getShell();
  }

  /**
   * 创建具体 DAO 类。
   */
  public static AbstractDAO<?> createDAO(String daoName) {
    return switch (daoName) {
      case "herb" -> new HerbDAO();
      case "experience" -> new ExperienceDAO();
      case "prescription-info" -> new PrescriptionInfoDAO();
      case "prescription" -> new PrescriptionDAO();
      case "item-differentiation-info" -> new ItemDifferentiationInfoDAO();
      case "item-differentiation" -> new ItemDifferentiationDAO();
      default -> null;
    };
  }

  /**
   * 运行指定 SQL 语句并使用指定参数列填充。目的主要在于设置。
   */
  public int runRawSQLToUpdate(String sql, Object... params) {
    int num = -1;
    try {
      ps = cs.getUsufruct().prepareStatement(sql);
      for (int i = 0; i < params.length; i++)
        ps.setObject(i + 1, params[i]);
      num = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionsPool.close(ps, rs);
    }
    return num;
  }

  /**
   * 运行指定 SQL 语句并使用指定参数列填充。目的主要在于获取。
   */
  public List<T> runRawSQLToQuery(RowMapper<T> rm, String sql, Object... params) {
    List<T> list = new ArrayList<>();
    try {
      ps = cs.getUsufruct().prepareStatement(sql);
      for (int i = 0; i < params.length; i++)
        ps.setObject(i + 1, params[i]);
      rs = ps.executeQuery();
      while (rs.next()) list.add(rm.mapRow(rs));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionsPool.close(ps, rs);
    }
    return list;
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

  @Override
  protected void finalize() {
    ConnectionsPool.returnShell(cs);
  }
}
