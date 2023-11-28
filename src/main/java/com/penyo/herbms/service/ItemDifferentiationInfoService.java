package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;

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
    Set<ItemDifferentiationInfoBean> idtis = new LinkedHashSet<>();
    idtis.addAll(idtiDAO.selectByFields(fields));
    idtis.addAll(selectByPrescriptionName(fields));
    return new ArrayList<>(idtis);
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public List<ItemDifferentiationInfoBean> selectByPrescriptionId(int id) {
    List<ItemDifferentiationInfoBean> idtis = new ArrayList<>();

    for (ItemDifferentiationBean o : idtDAO.selectAll())
      if (o.getPrescriptionId() == id)
        idtis.add(idtiDAO.selectById(idtDAO.selectById(o.getId()).getItemDifferentiationId()));
    return idtis;
  }

  /**
   * 根据处方名称返回条辩。
   */
  public List<ItemDifferentiationInfoBean> selectByPrescriptionName(List<String> fields) {
    List<ItemDifferentiationInfoBean> idtis = new ArrayList<>();

    for (PrescriptionInfoBean o : piDAO.selectByFields(fields))
      for (ItemDifferentiationBean oo : idtDAO.selectAll())
        if (o.getId() == oo.getPrescriptionId()) idtis.add(idtiDAO.selectById(oo.getItemDifferentiationId()));
    return idtis;
  }
}
