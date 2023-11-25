package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiationBean;

/**
 * 条辨的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
public class ItemDifferentiationDAO extends GenericDAO<ItemDifferentiationBean> {
  protected ItemDifferentiationDAO() {
    super("ItemDifferentiationMapper");
  }
}
