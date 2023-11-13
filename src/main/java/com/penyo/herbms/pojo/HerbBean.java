package com.penyo.herbms.pojo;

/**
 * 药品的数据容器。
 * 
 * @author Penyo
 */
public class HerbBean extends Bean {
  /** 唯一识别码 */
  private int id;
  /** 编号 */
  private int code;
  /** 学名 */
  private String name;
  /** 别名 */
  private String nickname;
  /** 归属类别 */
  private String type;
  /** 本经原文 */
  private String description;
  /** 主治 */
  private String efficacy;
  /** 性味 */
  private String taste;
  /** 产地 */
  private String origin;
  /** 用量 */
  private String dosage;
  /** 禁忌 */
  private String taboo;
  /** 炮制方法 */
  private String processing;

  public HerbBean() {
  }

  public HerbBean(int id, int code, String name, String nickname, String type, String description,
      String efficacy,
      String taste, String origin, String dosage, String taboo, String processing) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.nickname = nickname;
    this.type = type;
    this.description = description;
    this.efficacy = efficacy;
    this.taste = taste;
    this.origin = origin;
    this.dosage = dosage;
    this.taboo = taboo;
    this.processing = processing;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEfficacy() {
    return efficacy;
  }

  public void setEfficacy(String efficacy) {
    this.efficacy = efficacy;
  }

  public String getTaste() {
    return taste;
  }

  public void setTaste(String taste) {
    this.taste = taste;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDosage() {
    return dosage;
  }

  public void setDosage(String dosage) {
    this.dosage = dosage;
  }

  public String getTaboo() {
    return taboo;
  }

  public void setTaboo(String taboo) {
    this.taboo = taboo;
  }

  public String getProcessing() {
    return processing;
  }

  public void setProcessing(String processing) {
    this.processing = processing;
  }
}
