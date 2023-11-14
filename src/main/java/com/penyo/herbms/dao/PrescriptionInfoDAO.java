package com.penyo.herbms.dao;

import java.util.List;

import com.penyo.herbms.pojo.PrescriptionInfoBean;

/**
 * 处方概要的持久层。
 * 
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 */
public class PrescriptionInfoDAO extends DAO<PrescriptionInfoBean> {
  RowMapper<PrescriptionInfoBean> rm = (rs) -> {
    PrescriptionInfoBean pi = null;
    try {
      pi = new PrescriptionInfoBean(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("nickname"),
          rs.getString("description"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pi;
  };

  @Override
  public int add(PrescriptionInfoBean o) {
    final String SQL = "insert prescription_infos(name, nickname, description) values(?, ?, ?)";
    return runRawSQLToUpdate(SQL, o.getName(), o.getNickname(), o.getDescription());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from prescription_infos where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public PrescriptionInfoBean select(int id) {
    PrescriptionInfoBean pi = null;
    final String SQL = "select * from prescription_infos where id=?";
    List<PrescriptionInfoBean> pis = runRawSQLToQuery(rm, SQL, id);
    if (pis != null && pis.size() > 0)
      pi = pis.get(0);
    return pi;
  }

  @Override
  public List<PrescriptionInfoBean> selectAll() {
    final String SQL = "select * from prescription_infos";
    return runRawSQLToQuery(rm, SQL);
  }

  @Override
  public int update(PrescriptionInfoBean o) {
    final String SQL = "update prescription_infos set name=?, nickname=?, description=? where id=?";
    return runRawSQLToUpdate(SQL, o.getName(), o.getNickname(), o.getDescription(), o.getId());
  }
}
