package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.Herb;
import com.penyo.herbms.util.SessionPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 中草药的数据访问代理
 *
 * @author Penyo
 * @see Herb
 */
@Repository
public class HerbDAO extends GenericDAO<Herb> {
  public HerbDAO() {
    super("HerbMapper");
  }

  /**
   * 根据中草药使用心得 ID 查找单个元素。
   */
  public Herb selectByExperienceId(int id) {
    Herb o = null;

    try (SqlSession s = SessionPool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByExperienceId", id);
    }
    return o;
  }

  /**
   * 根据处方 ID 查找多个元素。
   */
  public List<Herb> selectByPrescriptionId(int id) {
    List<Herb> os = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByPrescriptionId", id));
    }
    return os;
  }
}
