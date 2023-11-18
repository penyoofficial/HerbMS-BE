package com.penyo.herbms.util;

import java.sql.Connection;
import java.util.Objects;

/**
 * 连接壳。即连接的再封装。
 *
 * @author Penyo
 */
public class ConnectionShell {
  /**
   * 唯一识别码
   */
  private final int id;
  /**
   * 连接实例
   */
  private final Connection c;
  /**
   * 连接占用状态
   */
  private boolean isAvailable = true;

  protected ConnectionShell(Connection c) {
    id = c.hashCode();
    this.c = c;
  }

  /**
   * 设置连接可用。
   */
  protected void enable() {
    this.isAvailable = true;
  }

  /**
   * 设置连接不可用。
   */
  protected void disable() {
    this.isAvailable = false;
  }

  /**
   * 对连接进行借用。
   */
  public Connection getUsufruct() {
    if (isAvailable) return c;
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConnectionShell that = (ConnectionShell) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
