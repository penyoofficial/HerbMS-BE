package com.penyo.herbms.pojo;

import java.beans.JavaBean;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 数据容器。
 */
@JavaBean
public abstract class Bean implements Serializable {
  /** 唯一识别码 */
  private int id;

  public Bean() {
  }

  public Bean(int id) {
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
    if (obj instanceof Bean)
      return getId() == ((Bean) obj).getId();
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("\"id\": " + getId() + ", ");
    try {
      Field[] fs = this.getClass().getDeclaredFields();
      for (Field f : fs) {
        f.setAccessible(true);
        sb.append("\"" + f.getName() + "\": \"" + f.get(this) + "\", ");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    String s = sb.toString();
    return "{ " + s.substring(0, s.length() - 2) + " }";
  }
}
