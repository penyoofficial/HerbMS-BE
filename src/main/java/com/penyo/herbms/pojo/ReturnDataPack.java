package com.penyo.herbms.pojo;

import java.util.List;

/**
 * 返回数据包
 *
 * @author Penyo
 */
public class ReturnDataPack<T> extends GenericBean {
  /**
   * 所查询表
   */
  private boolean needQueryA;
  /**
   * 影响行数
   */
  private int affectedRows;
  /**
   * 结果
   */
  private List<T> objs;

  public ReturnDataPack() {
  }

  public ReturnDataPack(List<T> objs) {
    this.objs = objs;
  }

  public ReturnDataPack(boolean needQueryA, int affectedRows, List<T> objs) {
    this.needQueryA = needQueryA;
    this.affectedRows = affectedRows;
    this.objs = objs;
  }

  @Override
  public int getId() {
    return -1;
  }

  public boolean isNeedQueryA() {
    return needQueryA;
  }

  public void setNeedQueryA(boolean needQueryA) {
    this.needQueryA = needQueryA;
  }

  public int getAffectedRows() {
    return affectedRows;
  }

  public void setAffectedRows(int affectedRows) {
    this.affectedRows = affectedRows;
  }

  public List<T> getList() {
    return objs;
  }

  public void setList(List<T> objs) {
    this.objs = objs;
  }
}
