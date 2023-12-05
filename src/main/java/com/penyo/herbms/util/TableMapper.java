package com.penyo.herbms.util;

/**
 * 表映射器
 *
 * @author Penyo
 */
public enum TableMapper {
  HERBS("herbs"), EXPERIENCES("experiences"), PRESCRIPTION_INFOS("prescription_infos"), PRESCRIPTIONS("prescription"), ITEM_DIFFERENTIATION_INFOS("item_differentiation_infos"), ITEM_DIFFERENTIATIONS("item_differentiations");

  private final String value;

  TableMapper(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
