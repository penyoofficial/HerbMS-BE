package net.penyo.herbms.dao;

import net.penyo.herbms.pojo.Prescription;
import net.penyo.herbms.util.Pool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 处方的数据访问代理
 *
 * @author Penyo
 * @see Prescription
 * @see GenericDAO
 */
@Repository
public class PrescriptionDAO extends GenericDAO<Prescription> {
  public PrescriptionDAO() {
    super("PrescriptionMapper");
  }

  /**
   * 根据处方 ID 查询单个元素。
   */
  public Prescription selectByPrescriptionId(int id) {
    Prescription o = null;

    try (SqlSession s = Pool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByPrescriptionId", id);
    }
    return o;
  }
}
