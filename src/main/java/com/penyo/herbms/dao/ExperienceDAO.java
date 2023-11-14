package com.penyo.herbms.dao;

import java.util.List;

import com.penyo.herbms.pojo.ExperienceBean;

/**
 * 药品使用心得的持久层。
 * 
 * @author Penyo
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
public class ExperienceDAO extends DAO<ExperienceBean> {
  RowMapper<ExperienceBean> rm = (rs) -> {
    ExperienceBean exp = null;
    try {
      exp = new ExperienceBean(
          rs.getInt("id"),
          rs.getInt("herbId"),
          rs.getString("derivation"),
          rs.getString("content"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return exp;
  };

  @Override
  public int add(ExperienceBean o) {
    final String SQL = "insert experiences(herbId, derivation, content) values(?, ?, ?)";
    return runRawSQLToUpdate(SQL, o.getHerbId(), o.getDerivation(), o.getContent());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from experiences where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public ExperienceBean select(int id) {
    ExperienceBean exp = null;
    final String SQL = "select * from experiences where id=?";
    List<ExperienceBean> exps = runRawSQLToQuery(rm, SQL, id);
    if (exps != null && exps.size() > 0)
      exp = exps.get(0);
    return exp;
  }

  @Override
  public List<ExperienceBean> selectAll() {
    final String SQL = "select * from experiences";
    return runRawSQLToQuery(rm, SQL);
  }

  @Override
  public int update(ExperienceBean o) {
    final String SQL = "update experiences set herbId=?, derivation=?, content=? where id=?";
    return runRawSQLToUpdate(SQL, o.getHerbId(), o.getDerivation(), o.getContent(), o.getId());
  }
}
