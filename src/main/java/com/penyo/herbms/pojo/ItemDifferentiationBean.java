package com.penyo.herbms.pojo;

import java.util.Map;

/**
 * 条辨的数据容器。
 *
 * @author Penyo
 */
public class ItemDifferentiationBean extends AbstractBean {
  /**
   * 条辨 ID（外键）
   */
  private int itemDifferentionId;
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

  public ItemDifferentiationBean(int id, int itemDifferentionId, int prescriptionId, String type) {
    super(id);
    this.itemDifferentionId = itemDifferentionId;
    this.prescriptionId = prescriptionId;
    setType(type);
  }

  public int getItemDifferentionId() {
    return itemDifferentionId;
  }

  public void setItemDifferentionId(int itemDifferentionId) {
    this.itemDifferentionId = itemDifferentionId;
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
    } catch (Exception e) {
      this.type = "对症";
      e.printStackTrace();
    }
  }
}
