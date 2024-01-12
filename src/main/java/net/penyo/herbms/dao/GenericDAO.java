package net.penyo.herbms.dao;

import net.penyo.herbms.pojo.GenericBean;
import net.penyo.herbms.util.Pool;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用数据访问代理
 *
 * @author Penyo
 * @see GenericBean
 * @see AbstractDAO
 */
public abstract class GenericDAO<UnknownBean extends GenericBean> implements AbstractDAO<UnknownBean> {
  /**
   * 映射器全名
   */
  protected final String fullMapperName;

  protected GenericDAO(String mapperName) {
    fullMapperName = "net.penyo.herbms.mapper." + mapperName;
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
