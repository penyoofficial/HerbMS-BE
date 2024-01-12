package net.penyo.herbms.dao;

import net.penyo.herbms.pojo.Herb;
import net.penyo.herbms.util.Pool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 中草药的数据访问代理
 *
 * @author Penyo
 * @see Herb
 * @see GenericDAO
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

    try (SqlSession s = Pool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByExperienceId", id);
    }
    return o;
  }

  /**
   * 根据处方 ID 查找多个元素。
   */
  public List<Herb> selectByPrescriptionId(int id) {
    List<Herb> os = new ArrayList<>();

    try (SqlSession s = Pool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByPrescriptionId", id));
    }
    return os;
  }
}
