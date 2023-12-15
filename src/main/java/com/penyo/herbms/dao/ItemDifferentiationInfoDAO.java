package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiationInfo;
import com.penyo.herbms.util.SessionPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 条辨概要的数据访问代理
 *
 * @author Penyo
 * @see ItemDifferentiationInfo
 */
@Repository
public class ItemDifferentiationInfoDAO extends GenericDAO<ItemDifferentiationInfo> {
  public ItemDifferentiationInfoDAO() {
    super("ItemDifferentiationInfoMapper");
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public List<ItemDifferentiationInfo> selectByPrescriptionId(int id) {
    List<ItemDifferentiationInfo> is = new ArrayList<>();

    try (SqlSession s = SessionPool.getSession()) {
      is.addAll(s.selectList(fullMapperName + ".selectByPrescriptionId", id));
    }
    return is;
  }
}