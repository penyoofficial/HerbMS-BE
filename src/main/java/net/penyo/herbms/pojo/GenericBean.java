package net.penyo.herbms.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * 通用数据容器
 *
 * @author Penyo
 * @see AbstractBean
 */
public abstract class GenericBean implements AbstractBean {
  @Override
  public String toString() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
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
