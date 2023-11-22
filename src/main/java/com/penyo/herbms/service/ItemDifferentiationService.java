package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.PrescriptionBean;

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
   * 根据处方返回条辩。
   */
  public ItemDifferentiationBean selectByPrescription(String... fields) {
    int neededId = -1;
    for (PrescriptionBean o : pDAO.selectAll())
      for (ItemDifferentiationBean oo : idtDAO.select(fields))
        if (o.getId() == oo.getPrescriptionId()) neededId = idtDAO.select(o.getPrescriptionId()).getId();
    return idtDAO.select(neededId);
  }
}
