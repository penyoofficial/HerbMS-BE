package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.PrescriptionBean;

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

  /**
   * 根据处方返回条辩。
   */
  public ItemDifferentiationBean selectByPrescription(List<String> fields) {
    int neededId = -1;
    for (PrescriptionBean o : pDAO.selectAll())
      for (ItemDifferentiationBean oo : idtDAO.selectByFields(fields))
        if (o.getId() == oo.getPrescriptionId()) neededId = idtDAO.selectById(o.getPrescriptionId()).getId();
    return idtDAO.selectById(neededId);
  }
}
