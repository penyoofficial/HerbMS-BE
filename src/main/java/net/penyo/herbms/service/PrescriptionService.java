package net.penyo.herbms.service;

import net.penyo.herbms.pojo.Prescription;
import net.penyo.herbms.pojo.PrescriptionInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处方的业务代理
 *
 * @author Penyo
 * @see Prescription
 * @see GenericService
 */
@Service
public class PrescriptionService extends GenericService<Prescription> {
  @Override
  public int add(Prescription o) {
    return prescriptionDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return prescriptionDAO.delete(id);
  }

  @Override
  public int update(Prescription o) {
    return prescriptionDAO.update(o);
  }

  @Override
  public Prescription selectById(int id) {
    return prescriptionDAO.selectById(id);
  }

  @Override
  public List<Prescription> selectByFields(List<String> fields) {
    return prescriptionDAO.selectByFields(fields);
  }

  /**
   * 根据处方 ID 查找处方概要。
   */
  public PrescriptionInfo selectPrescriptionInfoByPrescriptionId(int id) {
    return prescriptionInfoDAO.selectByPrescriptionId(id);
  }
}
