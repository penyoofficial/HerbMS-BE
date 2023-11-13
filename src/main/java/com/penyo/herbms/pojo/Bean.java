package com.penyo.herbms.pojo;

import java.beans.JavaBean;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 数据容器。
 */
@JavaBean
public class Bean implements Serializable {
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    try {
      Field[] fs = this.getClass().getDeclaredFields();
      for (Field f : fs) {
        f.setAccessible(true);
        sb.append(f.getName() + ": " + f.get(this) + ", ");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    String s = sb.toString();
    return "{ " + s.substring(0, s.length() - 2) + " }";
  }
}
