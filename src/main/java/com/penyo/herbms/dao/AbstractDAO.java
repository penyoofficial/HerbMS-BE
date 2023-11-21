package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.AbstractBean;
import com.penyo.herbms.util.DBUtil;
import com.penyo.tsington.v0.ConnectionShell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问代理
 *
 * @author Penyo
 */
public abstract class AbstractDAO<UncertainBean extends AbstractBean> {
  /**
   * 命令缓存
   */
  private PreparedStatement ps = null;
  /**
   * 结果缓存
   */
  private ResultSet rs = null;

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
    ConnectionShell cs = DBUtil.pool.lendShell();
    if (cs != null) try {
      ps = cs.getUsufruct().prepareStatement(sql);
      for (int i = 0; i < params.length; i++)
        ps.setObject(i + 1, params[i]);
      num = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(ps, rs);
    }
    DBUtil.pool.returnShell(cs);
    return num;
  }

  /**
   * 运行指定 SQL 语句并使用指定参数列填充。目的主要在于获取。
   */
  public List<UncertainBean> runRawSQLToQuery(RowMapper<UncertainBean> rm, String sql, Object... params) {
    List<UncertainBean> list = new ArrayList<>();
    ConnectionShell cs = DBUtil.pool.lendShell();
    if (cs != null) try {
      ps = cs.getUsufruct().prepareStatement(sql);
      for (int i = 0; i < params.length; i++)
        ps.setObject(i + 1, params[i]);
      rs = ps.executeQuery();
      while (rs.next()) list.add(rm.mapRow(rs));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(ps, rs);
    }
    DBUtil.pool.returnShell(cs);
    return list;
  }

  /**
   * 添加单个元素。
   */
  public abstract int add(UncertainBean o);

  /**
   * 删除单个元素。
   */
  public abstract int delete(int id);

  /**
   * 修改单个元素。
   */
  public abstract int update(UncertainBean o);

  /**
   * 查找单个元素。
   */
  public abstract UncertainBean select(int id);

  /**
   * 模糊查找元素。
   */
  public abstract List<UncertainBean> select(String... fields);

  /**
   * 查找全部元素。
   */
  public abstract List<UncertainBean> selectAll();
}
