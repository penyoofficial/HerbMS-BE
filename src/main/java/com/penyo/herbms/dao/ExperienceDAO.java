package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.Experience;
import com.penyo.herbms.util.Pool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 中草药使用心得的数据访问代理
 *
 * @author Penyo
 * @see Experience
 */
@Repository
public class ExperienceDAO extends GenericDAO<Experience> {
  public ExperienceDAO() {
    super("ExperienceMapper");
  }

  /**
   * 根据中草药使用心得 ID 查找多个元素。
   */
  public List<Experience> selectByHerbId(int id) {
    List<Experience> os = new ArrayList<>();

    try (SqlSession s = Pool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByHerbId", id));
    }
    return os;
  }
}
