package com.penyo.herbms.pojo;

import java.util.Map;

/**
 * 条辨的数据容器
 *
 * @author Penyo
 */
public class ItemDifferentiationBean extends GenericBean {
  /**
   * 唯一识别码
   */
  private int id;
  /**
   * 条辨 ID（外键）
   */
  private int itemDifferentiationId;
  /**
   * 处方 ID（外键）
   */
  private int prescriptionId;
  /**
   * 类型
   */
  private String type;

  public ItemDifferentiationBean() {
  }

  public ItemDifferentiationBean(int id, int itemDifferentiationId, int prescriptionId, String type) {
    this.id = id;
    this.itemDifferentiationId = itemDifferentiationId;
    this.prescriptionId = prescriptionId;
    setType(type);
  }

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getItemDifferentiationId() {
    return itemDifferentiationId;
  }

  public void setItemDifferentiationId(int itemDifferentiationId) {
    this.itemDifferentiationId = itemDifferentiationId;
  }

  public int getPrescriptionId() {
    return prescriptionId;
  }

  public void setPrescriptionId(int prescriptionId) {
    this.prescriptionId = prescriptionId;
  }

  public static boolean isTypeValid(String type) {
    final Map<String, Boolean> TYPES = Map.of("对症", true, "不适用", true, "其他", true);

    return TYPES.get(type) != null;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    try {
      if (!ItemDifferentiationBean.isTypeValid(type)) throw new TypeNotPresentException(type, null);
      this.type = type;
    } catch (TypeNotPresentException e) {
      this.type = "对症";
      e.printStackTrace();
    }
  }
}
