package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.Prescription;
import com.penyo.herbms.util.SessionPool;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 处方的数据访问代理
 *
 * @author Penyo
 * @see Prescription
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

    try (SqlSession s = SessionPool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByPrescriptionId", id);
    }
    return o;
  }
}
