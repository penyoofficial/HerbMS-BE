package com.penyo.herbms.service;

import com.penyo.herbms.pojo.PrescriptionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 经方的业务层
 *
 * @author hawkkie
 */
public class PrescriptionService extends AbstractService<PrescriptionBean> {
  @Override
  public int add(PrescriptionBean o) {
    return pDAO.add(o);
  }

  @Override
  public int deleteById(int id) {
    return pDAO.delete(id);
  }

  @Override
  public int update(PrescriptionBean o) {
    return pDAO.update(o);
  }

  @Override
  public PrescriptionBean selectById(int id) {
    return pDAO.select(id);
  }

  @Override
  public List<PrescriptionBean> selectByFields(String... fields) {
    return pDAO.select(fields);
  }

  /**
   * 根据字段查找元素。
   */
  public List<PrescriptionBean> selectByField(String field) {
    List<PrescriptionBean> ps = new ArrayList<>();
    for (PrescriptionBean h : pDAO.selectAll())
      if (h.getDosage().contains(field) || h.getUsage().contains(field)) ps.add(h);
    return ps;
  }
}
