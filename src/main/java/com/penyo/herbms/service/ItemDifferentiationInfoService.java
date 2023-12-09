package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
  public List<ItemDifferentiationInfoBean> selectByPrescriptionId(int id) {
    List<ItemDifferentiationInfoBean> o = new ArrayList<>();

    for (ItemDifferentiationInfoBean oo : idtiDAO.selectByPrescriptionId(id))
      o.add(oo);
    return o;
  }

  /**
   * 根据处方名称返回条辩。
   */
  public List<ItemDifferentiationInfoBean> selectByPrescriptionName(List<String> fields) {
    List<ItemDifferentiationInfoBean> o = new ArrayList<>();

    for (ItemDifferentiationInfoBean oo : idtiDAO.selectByPrescriptionName(fields))
      o.add(oo);
    return o;
  }
}
