package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.util.SessionPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 中药的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 */
public class HerbDAO extends GenericDAO<HerbBean> {
  protected HerbDAO() {
    super("HerbMapper");
  }

  /**
   * 根据中草药使用心得 ID 查找单个元素。
   */
  public HerbBean selectByExperienceId(int id) {
    HerbBean o = null;

    try (SqlSession s = SessionPool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByExperienceId", id);
    }
    return o;
  }

  /**
   * 根据处方 ID 查找多个元素。
   */
  public List<HerbBean> selectByPrescriptionId(int id) {
    List<HerbBean> os = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByPrescriptionId", id));
    }
    return os;
  }
}
