package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;

import java.util.ArrayList;
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
   * 根据字段查找元素。
   */
  public List<ItemDifferentiationInfoBean> selectByField(String field) {
    if (field.isEmpty()) return idtiDAO.selectAll();

    List<ItemDifferentiationInfoBean> idtis = new ArrayList<>();
    for (ItemDifferentiationInfoBean h : idtiDAO.selectAll())
      if (h.getAnnotation().contains(field) || h.getContent().contains(field)) idtis.add(h);
    return idtis;
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
