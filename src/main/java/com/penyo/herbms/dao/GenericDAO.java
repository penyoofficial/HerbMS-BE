package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.GenericBean;
import com.penyo.herbms.util.SessionPool;
import com.penyo.herbms.util.TableMapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 通用数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.dao.AbstractDAO
 */
public abstract class GenericDAO<UncertainBean extends GenericBean> implements AbstractDAO<UncertainBean> {
  /**
   * 映射器全名
   */
  protected final String fullMapperName;

  protected GenericDAO(String mapperName) {
    fullMapperName = "com.penyo.herbms.mapper." + mapperName;
  }

  /**
   * 创建具体 DAO 实例。
   */
  public static GenericDAO<?> createDAO(TableMapper t) {
    return switch (t) {
      case HERBS -> new HerbDAO();
      case EXPERIENCES -> new ExperienceDAO();
      case PRESCRIPTION_INFOS -> new PrescriptionInfoDAO();
      case PRESCRIPTIONS -> new PrescriptionDAO();
      case ITEM_DIFFERENTIATION_INFOS -> new ItemDifferentiationInfoDAO();
      case ITEM_DIFFERENTIATIONS -> new ItemDifferentiationDAO();
    };
  }

  @Override
  public final synchronized int add(UncertainBean o) {
    if (selectById(o.getId()) != null) return 0;

    int affectedRows = 0;
    try (SqlSession s = SessionPool.getSession()) {
      affectedRows = s.insert(fullMapperName + ".add", o);
      s.commit();
    }
    return affectedRows;
  }

  @Override
  public final synchronized int delete(int id) {
    int affectedRows = 0;
    try (SqlSession s = SessionPool.getSession()) {
      affectedRows = s.delete(fullMapperName + ".delete", id);
      s.commit();
    }
    return affectedRows;
  }

  @Override
  public final synchronized int update(UncertainBean o) {
    if (selectById(o.getId()) == null) return 0;

    int affectedRows = 0;
    try (SqlSession s = SessionPool.getSession()) {
      affectedRows = s.update(fullMapperName + ".update", o);
      s.commit();
    }
    return affectedRows;
  }

  @Override
  public final UncertainBean selectById(int id) {
    UncertainBean o = null;

    try (SqlSession s = SessionPool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectById", id);
    }
    return o;
  }

  @Override
  public final List<UncertainBean> selectByFields(List<String> fields) {
    if (fields.size() <= 1 && fields.get(0).isEmpty()) return selectAll();

    List<UncertainBean> os = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByFields", fields));
    }
    return os;
  }

  @Override
  public final List<UncertainBean> selectAll() {
    List<UncertainBean> os = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectAll"));
    }
    return os;
  }
}
