package com.penyo.herbms.pojo;

/**
 * 处方的数据容器
 *
 * @author Penyo
 */
public class Prescription extends GenericBean {
  /**
   * 唯一识别码
   */
  private int id;
  /**
   * 中草药处方 ID（外键）
   */
  private int prescriptionId;
  /**
   * 中草药 ID（外键）
   */
  private int herbId;
  /**
   * 用量
   */
  private String dosage;
  /**
   * 用法
   */
  private String usage;

  public Prescription() {
  }

  public Prescription(int id, int prescriptionId, int herbId, String dosage, String usage) {
    this.id = id;
    this.prescriptionId = prescriptionId;
    this.herbId = herbId;
    this.dosage = dosage;
    this.usage = usage;
  }

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPrescriptionId() {
    return prescriptionId;
  }

  public void setPrescriptionID(int prescriptionId) {
    this.prescriptionId = prescriptionId;
  }

  public int getHerbId() {
    return herbId;
  }

  public void setHerbId(int herbId) {
    this.herbId = herbId;
  }

  public String getDosage() {
    return dosage;
  }

  public void setDosage(String dosage) {
    this.dosage = dosage;
  }

  public String getUsage() {
    return usage;
  }

  public void setUsage(String usage) {
    this.usage = usage;
  }
}
