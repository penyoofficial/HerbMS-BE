package com.penyo.herbms.dao;

import java.sql.ResultSet;

/**
 * 行映射器。
 */
public interface RowMapper<T> {
  /**
   * 对数据结果进行重包装。
   */
  public T repack(ResultSet rs);
}
