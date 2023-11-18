package com.penyo.herbms.pojo;

/**
 * 条辨概要的数据容器。
 *
 * @author Penyo
 */
public class ItemDifferentiationInfoBean extends AbstractBean {
  /**
   * 编号
   */
  private int code;
  /**
   * 内容
   */
  private String content;
  /**
   * 注释
   */
  private String annotation;

  public ItemDifferentiationInfoBean() {
  }

  public ItemDifferentiationInfoBean(int id, int code, String content, String annotation) {
    super(id);
    this.code = code;
    this.content = content;
    this.annotation = annotation;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAnnotation() {
    return annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }
}
