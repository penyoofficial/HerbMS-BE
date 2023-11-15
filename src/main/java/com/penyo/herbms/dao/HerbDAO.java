package com.penyo.herbms.dao;

import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.pojo.HerbBean;

/**
 * 药品的持久层。
 * 
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 */
public class HerbDAO extends DAO<HerbBean> {
  RowMapper<HerbBean> rm = (rs) -> {
    HerbBean h = null;
    try {
      h = new HerbBean(
          rs.getInt("id"),
          rs.getInt("code"),
          rs.getString("name"),
          rs.getString("nickname"),
          rs.getString("type"),
          rs.getString("description"),
          rs.getString("efficacy"),
          rs.getString("taste"),
          rs.getString("origin"),
          rs.getString("dosage"),
          rs.getString("taboo"),
          rs.getString("processing"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return h;
  };

  @Override
  public int add(HerbBean o) {
    final String SQL = "insert herbs(code, name, nickname, type, description, efficacy, taste, origin, dosage, taboo, processing) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return runRawSQLToUpdate(SQL,
        o.getCode(),
        o.getName(),
        o.getNickname(),
        o.getType(),
        o.getDescription(),
        o.getEfficacy(),
        o.getTaste(),
        o.getOrigin(),
        o.getDosage(),
        o.getTaboo(),
        o.getProcessing());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from herbs where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public HerbBean select(int id) {
    HerbBean h = null;
    final String SQL = "select * from herbs where id=?";
    List<HerbBean> hs = runRawSQLToQuery(rm, SQL, id);
    if (hs != null && hs.size() > 0)
      h = hs.get(0);
    return h;
  }

  @Override
  public List<HerbBean> selectAll() {
    final String SQL = "select * from herbs";
    return runRawSQLToQuery(rm, SQL);
  }

  @Override
  public int update(HerbBean o) {
    final String SQL = "update herbs set code=?, name=?, nickname=?, type=?, description=?, efficacy=?, taste=?, origin=?, dosage=?, taboo=?, processing=? where id=?";
    return runRawSQLToUpdate(SQL,
        o.getCode(),
        o.getName(),
        o.getNickname(),
        o.getType(),
        o.getDescription(),
        o.getEfficacy(),
        o.getTaste(),
        o.getOrigin(),
        o.getDosage(),
        o.getTaboo(),
        o.getProcessing(),
        o.getId());
  }

  /**
   * 根据字段查找元素。
   */
  public List<HerbBean> selectByField(String field) {
    List<HerbBean> hs = new ArrayList<HerbBean>();
    for (HerbBean h : selectAll())
      if (h.getName().contains(field)
          || h.getNickname().contains(field)
          || h.getType().contains(field)
          || h.getDescription().contains(field)
          || h.getEfficacy().contains(field)
          || h.getTaste().contains(field)
          || h.getOrigin().contains(field)
          || h.getDosage().contains(field)
          || h.getTaboo().contains(field)
          || h.getProcessing().contains(field))
        hs.add(h);
    return hs;
  }

  /**
   * 根据心得查找元素。
   */
  public List<HerbBean> selectByExperience(String exp) {
    List<HerbBean> hs = new ArrayList<HerbBean>();
    for (ExperienceBean e : new ExperienceDAO().selectAll())
      if (e.getContent().equals(exp))
        hs.add(select(e.getHerbId()));
    return hs;
  }
}
