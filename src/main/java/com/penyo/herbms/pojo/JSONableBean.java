package com.penyo.herbms.pojo;

import java.beans.JavaBean;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 可 JSON 化的数据容器
 *
 * @author Penyo
 */
@JavaBean
public abstract class JSONableBean implements Serializable {
  @Override
  public String toString() {
    StringBuilder jsonT1 = new StringBuilder();
    try {
      Field[] fs = this.getClass().getDeclaredFields();

      for (Field f : fs) {
        f.setAccessible(true);

        StringBuilder valueJSON = new StringBuilder();
        Object value = f.get(this);
        if (value instanceof Integer) valueJSON = new StringBuilder(((Integer) value).toString());
        else if (value instanceof Double) valueJSON = new StringBuilder(((Double) value).toString());
        else if (value instanceof Boolean) valueJSON = new StringBuilder(((Boolean) value).toString());
        else if (value instanceof String) valueJSON = new StringBuilder("\"" + value + "\"");
        else if (value instanceof List) {
          StringBuilder jsonT2 = new StringBuilder();
          for (JSONableBean o : (List<JSONableBean>) value)
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
}
