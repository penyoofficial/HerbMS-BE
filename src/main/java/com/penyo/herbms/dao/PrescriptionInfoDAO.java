package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.PrescriptionInfo;
import com.penyo.herbms.util.Pool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 处方概要的数据访问代理
 *
 * @author Penyo
 * @see PrescriptionInfo
 */
@Repository
public class PrescriptionInfoDAO extends GenericDAO<PrescriptionInfo> {
  public PrescriptionInfoDAO() {
    super("PrescriptionInfoMapper");
  }

  /**
   * 根据处方 ID 查询单个元素。
   */
  public PrescriptionInfo selectByPrescriptionId(int id) {
    PrescriptionInfo o = null;

    try (SqlSession s = Pool.getSession()) {
      o = s.selectOne(fullMapperName + ".selectByPrescriptionId", id);
    }
    return o;
  }

  /**
   * 根据条辩 ID 查询多个元素。
   */
  public List<PrescriptionInfo> selectByIDTIId(int id) {
    List<PrescriptionInfo> os = new ArrayList<>();

    try (SqlSession s = Pool.getSession()) {
      os.addAll(s.selectList(fullMapperName + ".selectByIDTIId", id));
    }
    return os;
  }


}
