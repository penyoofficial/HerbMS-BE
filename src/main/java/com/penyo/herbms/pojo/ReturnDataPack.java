package com.penyo.herbms.pojo;

import java.util.List;

/**
 * 返回数据包
 *
 * @author Penyo
 */
public class ReturnDataPack<UncertainBean extends JSONableBean> extends JSONableBean {
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
  private List<UncertainBean> objs;

  public ReturnDataPack() {
  }

  public ReturnDataPack(boolean needQueryA, int affectedRows, List<UncertainBean> objs) {
    this.needQueryA = needQueryA;
    this.affectedRows = affectedRows;
    this.objs = objs;
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

  public List<UncertainBean> getList() {
    return objs;
  }

  public void setList(List<UncertainBean> objs) {
    this.objs = objs;
  }
}
