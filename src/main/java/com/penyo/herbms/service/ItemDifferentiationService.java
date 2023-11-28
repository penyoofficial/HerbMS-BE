package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationBean;

import java.util.List;

/**
 * 条辩的业务代理
 *
 * @author Penyo
 */
public class ItemDifferentiationService extends GenericService<ItemDifferentiationBean> {
  @Override
  public int add(ItemDifferentiationBean o) {
    return idtDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return idtDAO.delete(id);
  }

  @Override
  public int update(ItemDifferentiationBean o) {
    return idtDAO.update(o);
  }

  @Override
  public ItemDifferentiationBean selectById(int id) {
    return idtDAO.selectById(id);
  }

  @Override
  public List<ItemDifferentiationBean> selectByFields(List<String> fields) {
    return idtDAO.selectByFields(fields);
  }
}
