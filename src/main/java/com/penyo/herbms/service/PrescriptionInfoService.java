package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.PrescriptionBean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 经方概要的业务代理
 *
 * @author Penyo
 */
public class PrescriptionInfoService extends GenericService<PrescriptionInfoBean> {
  @Override
  public int add(PrescriptionInfoBean o) {
    return piDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return piDAO.delete(id);
  }

  @Override
  public int update(PrescriptionInfoBean o) {
    return piDAO.update(o);
  }

  @Override
  public PrescriptionInfoBean selectById(int id) {
    return piDAO.selectById(id);
  }

  @Override
  public List<PrescriptionInfoBean> selectByFields(List<String> fields) {
    Set<PrescriptionInfoBean> pis = new LinkedHashSet<>();
    pis.addAll(piDAO.selectByFields(fields));
    pis.addAll(selectByHerbName(fields));
    return new ArrayList<>(pis);
  }

  /**
   * 根据中草药名称查询元素。
   */
  public List<PrescriptionInfoBean> selectByHerbName(List<String> fields) {
    List<PrescriptionInfoBean> pis = new ArrayList<>();

    for (PrescriptionBean o : pDAO.selectAll())
      for (HerbBean oo : hDAO.selectByFields(fields))
        if (o.getHerbId() == oo.getId()) pis.add(piDAO.selectById(pDAO.selectById(o.getHerbId()).getPrescriptionId()));
    return pis;
  }

  /**
   * 根据处方 ID 查找元素。
   */
  public PrescriptionInfoBean selectByPrescriptionId(int id) {
    for (PrescriptionBean o : pDAO.selectAll())
      if (o.getId() == id) return piDAO.selectById(o.getPrescriptionId());
    return null;
  }

  /**
   * 根据条辨 ID 查找元素。
   */
  public List<PrescriptionInfoBean> selectByIDTIId(int id) {
    List<PrescriptionInfoBean> pis = new ArrayList<>();

    for (ItemDifferentiationBean o : idtDAO.selectAll())
      if (o.getItemDifferentiationId() == id)
        pis.add(piDAO.selectById(idtDAO.selectById(o.getId()).getPrescriptionId()));
    return pis;
  }
}
