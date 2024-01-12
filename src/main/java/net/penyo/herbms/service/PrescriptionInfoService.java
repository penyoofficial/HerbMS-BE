package net.penyo.herbms.service;

import net.penyo.herbms.pojo.PrescriptionInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 处方概要的业务代理
 *
 * @author Penyo
 * @see PrescriptionInfo
 * @see GenericService
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
