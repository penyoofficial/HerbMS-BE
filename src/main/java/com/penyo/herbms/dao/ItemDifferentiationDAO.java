package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiation;
import org.springframework.stereotype.Repository;

/**
 * 条辨的数据访问代理
 *
 * @author Penyo
 * @see ItemDifferentiation
 */
@Repository
public class ItemDifferentiationDAO extends GenericDAO<ItemDifferentiation> {
  public ItemDifferentiationDAO() {
    super("ItemDifferentiationMapper");
  }
}
