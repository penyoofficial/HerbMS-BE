package net.penyo.herbms.pojo;

import java.util.List;

/**
 * 返回数据包
 *
 * @author Penyo
 * @see GenericBean
 */
public class ReturnDataPack<T> extends GenericBean {
  /**
   * 影响行数
   */
  private final int affectedRows;
  /**
   * 结果
   */
  private final List<T> objs;

  public ReturnDataPack(List<T> objs) {
    this.affectedRows = -114514;
    this.objs = objs;
  }

  public ReturnDataPack(int affectedRows, List<T> objs) {
    this.affectedRows = affectedRows;
    this.objs = objs;
  }

  public int getAffectedRows() {
    return affectedRows;
  }

  public List<T> getObjs() {
    return objs;
  }
}
