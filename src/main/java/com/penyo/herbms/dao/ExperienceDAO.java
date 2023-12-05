package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.util.SessionPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 中药使用心得的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
public class ExperienceDAO extends GenericDAO<ExperienceBean> {
  protected ExperienceDAO() {
    super("ExperienceMapper");
  }

  /**
   * 根据中草药使用心得 ID 查找多个元素。
   */
  public List<ExperienceBean> selectByHerbId(int id) {
    List<ExperienceBean> os = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByHerbId", id));
    }
    return os;
  }
}
