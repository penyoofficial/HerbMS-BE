package com.penyo.herbms.pojo;

import java.beans.JavaBean;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 数据容器
 *
 * @author Penyo
 */
@JavaBean
public abstract class AbstractBean implements Serializable {
  /**
   * 唯一识别码
   */
  private int id;

  public AbstractBean() {
  }

  public AbstractBean(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AbstractBean) return getId() == ((AbstractBean) obj).getId();
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("\"id\": " + getId() + ", ");
    try {
      Field[] fs = this.getClass().getDeclaredFields();
      for (Field f : fs) {
        f.setAccessible(true);
        sb.append("\"").append(f.getName()).append("\": \"").append(f.get(this)).append("\", ");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    String s = sb.toString();
    return "{ " + s.substring(0, s.length() - 2) + " }";
  }
}
