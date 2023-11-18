package com.penyo.herbms.dao;

import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.pojo.PrescriptionInfoBean;

/**
 * 处方概要的数据访问代理。
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 */
public class PrescriptionInfoDAO extends AbstractDAO<PrescriptionInfoBean> {
  final RowMapper<PrescriptionInfoBean> rm = (rs) -> {
    PrescriptionInfoBean pi = null;
    try {
      pi = new PrescriptionInfoBean(rs.getInt("id"), rs.getString("name"), rs.getString("nickname"), rs.getString("description"));
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
    if (pis != null && pis.size() > 0) pi = pis.get(0);
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

  /**
   * 根据字段查找元素。
   */
  public List<PrescriptionInfoBean> selectByField(String field) {
    List<PrescriptionInfoBean> ps = new ArrayList<>();
    for (PrescriptionInfoBean h : selectAll())
      if (h.getName().contains(field) || h.getNickname().contains(field) || h.getDescription().contains(field))
        ps.add(h);
    return ps;
  }

  /**
   * 根据条辨 ID 查找元素。
   */
  public PrescriptionInfoBean selectByItemDifferentiationId(int id) {
    int neededId = -1;
    ItemDifferentiationDAO idtDAO = new ItemDifferentiationDAO();
    neededId = idtDAO.select(id).getPrescriptionId();

    return select(neededId);
  }
}
