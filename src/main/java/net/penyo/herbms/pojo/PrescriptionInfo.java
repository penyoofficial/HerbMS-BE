package net.penyo.herbms.pojo;

/**
 * 处方概要的数据容器
 *
 * @author Penyo
 * @see GenericBean
 */
public class PrescriptionInfo extends GenericBean {
  /**
   * 唯一识别码
   */
  private int id;
  /**
   * 名称
   */
  private String name;
  /**
   * 别名
   */
  private String nickname;
  /**
   * 解释
   */
  private String description;

  public PrescriptionInfo() {
  }

  public PrescriptionInfo(int id, String name, String nickname, String description) {
    this.id = id;
    this.name = name;
    this.nickname = nickname;
    this.description = description;
  }

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
