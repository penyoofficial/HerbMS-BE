package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.PrescriptionBean;

import java.util.List;

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
    return piDAO.selectByFields(fields);
  }

  /**
   * 根据中草药查询元素。
   */
  public PrescriptionInfoBean selectByHerbName(List<String> fields) {
    int neededId = -1;
    for (PrescriptionBean o : pDAO.selectAll())
      for (HerbBean oo : hDAO.selectByFields(fields))
        if (o.getHerbId() == oo.getId()) neededId = pDAO.selectById(o.getHerbId()).getPrescriptionId();
    return piDAO.selectById(neededId);
  }
}
