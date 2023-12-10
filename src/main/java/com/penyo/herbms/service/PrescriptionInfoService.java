package com.penyo.herbms.service;

import com.penyo.herbms.pojo.PrescriptionInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 处方概要的业务代理
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
   * 根据条辨 ID 查找多个经方名称。
   */
  public List<String> selectNamesByIDTIId(int id) {
    List<String> names = new ArrayList<>();

    for (PrescriptionInfoBean o : piDAO.selectByIDTIId(id))
      names.add(o.getName());
    return names;
  }
}
