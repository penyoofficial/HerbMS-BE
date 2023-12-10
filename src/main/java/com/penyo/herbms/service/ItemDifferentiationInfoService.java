package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;

import java.util.ArrayList;
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
   * 根据处方 ID 查询多个条辨内容。
   */
  public List<String> selectContentsByPrescriptionId(int id) {
    List<String> contents = new ArrayList<>();

    for (ItemDifferentiationInfoBean oo : idtiDAO.selectByPrescriptionId(id))
      contents.add(oo.getContent());
    return contents;
  }
}
