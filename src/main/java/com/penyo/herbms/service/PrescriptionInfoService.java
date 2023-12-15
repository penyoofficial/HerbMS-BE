package com.penyo.herbms.service;

import com.penyo.herbms.pojo.PrescriptionInfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 处方概要的业务代理
 *
 * @author Penyo
 */
@Service
public class PrescriptionInfoService extends GenericService<PrescriptionInfo> {
  @Override
  public int add(PrescriptionInfo o) {
    return prescriptionInfoDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return prescriptionInfoDAO.delete(id);
  }

  @Override
  public int update(PrescriptionInfo o) {
    return prescriptionInfoDAO.update(o);
  }

  @Override
  public PrescriptionInfo selectById(int id) {
    return prescriptionInfoDAO.selectById(id);
  }

  @Override
  public List<PrescriptionInfo> selectByFields(List<String> fields) {
    return prescriptionInfoDAO.selectByFields(fields);
  }

  /**
   * 根据条辨 ID 查找多个经方名称。
   */
  public List<String> selectNamesByIDTIId(int id) {
    List<String> names = new ArrayList<>();

    for (PrescriptionInfo o : prescriptionInfoDAO.selectByIDTIId(id))
      names.add(o.getName());
    return names;
  }
}
