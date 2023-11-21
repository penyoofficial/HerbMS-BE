package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;

import java.util.List;

/**
 * 条辩概要的业务层
 *
 * @author lyh
 */
public class ItemDifferentiationInfoService extends AbstractService<ItemDifferentiationInfoBean> {
  @Override
  public int add(ItemDifferentiationInfoBean o) {
    return idtiDAO.add(o);
  }

  @Override
  public int deleteById(int id) {
    return idtiDAO.delete(id);
  }

  @Override
  public int update(ItemDifferentiationInfoBean o) {
    return idtiDAO.update(o);
  }

  @Override
  public ItemDifferentiationInfoBean selectById(int id) {
    return idtiDAO.select(id);
  }

  @Override
  public List<ItemDifferentiationInfoBean> selectByFields(String... fields) {
    return idtiDAO.select(fields);
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public ItemDifferentiationInfoBean selectByPrescriptionId(int id) {
    int neededId = -1;
    for (ItemDifferentiationBean o : idtDAO.selectAll())
      if (o.getPrescriptionId() == pDAO.select(id).getPrescriptionId())
        neededId = idtDAO.select(o.getId()).getItemDifferentionId();
    return idtiDAO.select(neededId);
  }
}
