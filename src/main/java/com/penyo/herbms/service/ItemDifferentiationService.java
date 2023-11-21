package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 条辩的业务层
 *
 * @author lyh
 */
public class ItemDifferentiationService extends AbstractService<ItemDifferentiationBean> {
  @Override
  public int add(ItemDifferentiationBean o) {
    return idtDAO.add(o);
  }

  @Override
  public int deleteById(int id) {
    return idtDAO.delete(id);
  }

  @Override
  public int update(ItemDifferentiationBean o) {
    return idtDAO.update(o);
  }

  @Override
  public ItemDifferentiationBean selectById(int id) {
    return idtDAO.select(id);
  }

  @Override
  public List<ItemDifferentiationBean> selectByFields(String... fields) {
    return idtDAO.select(fields);
  }

  /**
   * 根据字段查找元素。
   */
  public List<ItemDifferentiationBean> selectByField(String field) {
    if (field.isEmpty()) return idtDAO.selectAll();
    List<ItemDifferentiationBean> idts = new ArrayList<>();
    for (ItemDifferentiationBean h : idtDAO.selectAll())
      if (h.getType().contains(field)) idts.add(h);
    return idts;
  }
}
