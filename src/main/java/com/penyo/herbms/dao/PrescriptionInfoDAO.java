package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.util.SessionPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 处方概要的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 */
public class PrescriptionInfoDAO extends GenericDAO<PrescriptionInfoBean> {
  protected PrescriptionInfoDAO() {
    super("PrescriptionInfoMapper");
  }

/**
 * 根据中草药名查询多个元素。
 */
  public List<PrescriptionInfoBean> selectByHerbName(int id){
    List<PrescriptionInfoBean> os = new ArrayList<>();
    try(SqlSession s = SessionPool.getSession()){
      os.addAll(s.selectList(fullMapperName + ".selectByHerbName",id));
    }
    return os;
  }

  /**
   * 根据处方 ID 查询单个元素。
   */
  public PrescriptionInfoBean selectByPrescriptionId(int id) {
    PrescriptionInfoBean o = null;

    try(SqlSession s = SessionPool.getSession()){
      o = s.selectOne(fullMapperName + ".selectByPrescriptionId",id);
    }
    return o;
  }

  /**
   * 根据条辩 ID 查询多个元素。
   */
  public List<PrescriptionInfoBean> selectByIDTIId(int id) {
    List<PrescriptionInfoBean> os = new ArrayList<>();

    try(SqlSession s = SessionPool.getSession()){
      os.addAll(s.selectList(fullMapperName + ".selectByIDTIId",id));
    }
    return os;
  }


}
