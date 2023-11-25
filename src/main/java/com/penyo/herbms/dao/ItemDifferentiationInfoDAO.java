package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;

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
}
