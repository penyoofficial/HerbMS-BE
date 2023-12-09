package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.util.SessionPool;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 条辨概要的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 */
public class ItemDifferentiationInfoDAO extends GenericDAO<ItemDifferentiationInfoBean> {
  protected ItemDifferentiationInfoDAO() {
    super("ItemDifferentiationInfoMapper");
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public List<ItemDifferentiationInfoBean> selectByPrescriptionId(int id) {
    List<ItemDifferentiationInfoBean> is = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      is.addAll(s.selectList(fullMapperName + ".selectByPrescriptionId", id));
    }
    return is;
  }

  /**
   * 根据处方名称返回条辩。
   */
  public List<ItemDifferentiationInfoBean> selectByPrescriptionName(List<String> fields) {
    List<ItemDifferentiationInfoBean> is = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      is.addAll(s.selectList(fullMapperName + ".selectByPrescriptionName", fields));
    }
    return is;
  }
}