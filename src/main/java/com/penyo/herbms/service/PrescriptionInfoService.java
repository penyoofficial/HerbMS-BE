package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.PrescriptionBean;

import java.util.List;

/**
 * 经方概要的业务层
 *
 * @author hawkkie
 */
public class PrescriptionInfoService extends AbstractService<PrescriptionInfoBean> {
  @Override
  public int add(PrescriptionInfoBean o) {
    return piDAO.add(o);
  }

  @Override
  public int deleteById(int id) {
    return piDAO.delete(id);
  }

  @Override
  public int update(PrescriptionInfoBean o) {
    return piDAO.update(o);
  }

  @Override
  public PrescriptionInfoBean selectById(int id) {
    return piDAO.select(id);
  }

  @Override
  public List<PrescriptionInfoBean> selectByFields(String... fields) {
    return piDAO.select(fields);
  }

  /**
   * 根据中草药查询元素。
   */
  public PrescriptionInfoBean selectByHerbName(String... fields) {
    int neededId = -1;
    for (PrescriptionBean o : pDAO.selectAll())
      for (HerbBean oo : hDAO.select(fields))
        if (o.getHerbId() == oo.getId()) neededId = pDAO.select(o.getHerbId()).getPrescriptionId();
    return piDAO.select(neededId);
  }
}
