package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;

import java.util.List;

/**
 * 条辩概要的业务代理
 *
 * @author Penyo
 */
public class ItemDifferentiationInfoService extends GenericService<ItemDifferentiationInfoBean> {
  @Override
  public int add(ItemDifferentiationInfoBean o) {
    return idtiDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return idtiDAO.delete(id);
  }

  @Override
  public int update(ItemDifferentiationInfoBean o) {
    return idtiDAO.update(o);
  }

  @Override
  public ItemDifferentiationInfoBean selectById(int id) {
    return idtiDAO.selectById(id);
  }

  @Override
  public List<ItemDifferentiationInfoBean> selectByFields(List<String> fields) {
    return idtiDAO.selectByFields(fields);
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public ItemDifferentiationInfoBean selectByPrescriptionId(int id) {
    int neededId = -1;
    for (ItemDifferentiationBean o : idtDAO.selectAll())
      if (o.getPrescriptionId() == pDAO.selectById(id).getPrescriptionId())
        neededId = idtDAO.selectById(o.getId()).getItemDifferentiationId();
    return idtiDAO.selectById(neededId);
  }
}
