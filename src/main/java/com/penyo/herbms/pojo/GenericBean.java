package com.penyo.herbms.pojo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * 通用数据容器
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.AbstractBean
 */
public abstract class GenericBean implements AbstractBean {
  /**
   * 获取唯一识别码。
   */
  public abstract int getId();

  @Override
  public String toString() {
    StringBuilder jsonT1 = new StringBuilder();
    try {
      Field[] fs = this.getClass().getDeclaredFields();

      for (Field f : fs) {
        f.setAccessible(true);

        StringBuilder valueJSON = new StringBuilder();
        Object value = f.get(this);
        if (value instanceof Integer v) valueJSON = new StringBuilder(v.toString());
        else if (value instanceof Double v) valueJSON = new StringBuilder(v.toString());
        else if (value instanceof Boolean v) valueJSON = new StringBuilder(v.toString());
        else if (value instanceof String v) valueJSON = new StringBuilder("\"" + v + "\"");
        else if (value instanceof List<?> v) {
          StringBuilder jsonT2 = new StringBuilder();
          for (var o : v)
            jsonT2.append(o.toString()).append(", ");

          String s = jsonT2.toString();
          if (s.length() > 2) valueJSON.append("[").append(s, 0, s.length() - 2).append("]");
          else valueJSON.append("[]");
        }

        jsonT1.append("\"").append(f.getName()).append("\": ").append(valueJSON).append(", ");
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }

    String s = jsonT1.toString();
    if (s.length() > 2) return "{ " + s.substring(0, s.length() - 2) + " }";
    return "{ }";
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof GenericBean safeObj)) return false;
    return safeObj.getId() == this.getId();
  }
}
