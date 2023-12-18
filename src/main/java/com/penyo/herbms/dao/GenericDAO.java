package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.GenericBean;
import com.penyo.herbms.util.Pool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 通用数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.dao.AbstractDAO
 */
public abstract class GenericDAO<UnknownBean extends GenericBean> implements AbstractDAO<UnknownBean> {
  /**
   * 映射器全名
   */
  protected final String fullMapperName;

  protected GenericDAO(String mapperName) {
    fullMapperName = "com.penyo.herbms.mapper." + mapperName;
  }

  @Override
  public final synchronized int add(UnknownBean o) {
    if (selectById(o.getId()) != null) return 0;

    int affectedRows = 0;
    try (SqlSession s = Pool.getSession()) {
      affectedRows = s.insert(fullMapperName + ".add", o);
      s.commit();
    }
    return affectedRows;
  }

  @Override
  public final synchronized int delete(int id) {
    int affectedRows = 0;
    try (SqlSession s = Pool.getSession()) {
      affectedRows = s.delete(fullMapperName + ".delete", id);
      s.commit();
    }
    return affectedRows;
  }

  @Override
  public final synchronized int update(UnknownBean o) {
    if (selectById(o.getId()) == null) return 0;

    int affectedRows = 0;
    try (SqlSession s = Pool.getSession()) {
      affectedRows = s.update(fullMapperName + ".update", o);
      s.commit();
    }
    return affectedRows;
  }

  @Override
  public final UnknownBean selectById(int id) {
    UnknownBean o = null;

    try (SqlSession s = Pool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectById", id);
    }
    return o;
  }

  @Override
  public final List<UnknownBean> selectByFields(List<String> fields) {
    if (fields.size() <= 1 && fields.get(0).isEmpty()) return selectAll();

    List<UnknownBean> os = new ArrayList<>();

    try (SqlSession s = Pool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByFields", fields));
    }
    return os;
  }

  @Override
  public final List<UnknownBean> selectAll() {
    List<UnknownBean> os = new ArrayList<>();

    try (SqlSession s = Pool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectAll"));
    }
    return os;
  }
}
