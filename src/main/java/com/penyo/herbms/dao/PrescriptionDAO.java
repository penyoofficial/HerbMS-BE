package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.PrescriptionBean;
import com.penyo.herbms.util.SessionPool;

import org.apache.ibatis.session.SqlSession;

/**
 * 处方的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
public class PrescriptionDAO extends GenericDAO<PrescriptionBean> {
  protected PrescriptionDAO() {
    super("PrescriptionMapper");
  }

  /**
   * 根据处方 ID 查询单个元素。
   */
  public PrescriptionBean selectByPrescriptionId(int id) {
    PrescriptionBean o = null;

    try (SqlSession s = SessionPool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByPrescriptionId", id);
    }
    return o;
  }
}
