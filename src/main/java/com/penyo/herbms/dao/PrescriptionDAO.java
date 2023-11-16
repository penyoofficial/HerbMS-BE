package com.penyo.herbms.dao;

import java.util.ArrayList;
import java.util.List;
import com.penyo.herbms.pojo.PrescriptionBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;

/**
 * 处方的持久层。
 * 
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
public class PrescriptionDAO extends DAO<PrescriptionBean> {
  RowMapper<PrescriptionBean> rm = (rs) -> {
    PrescriptionBean p = null;
    try {
      p = new PrescriptionBean(
          rs.getInt("id"),
          rs.getInt("prescriptionId"),
          rs.getInt("herbId"),
          rs.getString("dosage"),
          rs.getString("usage"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return p;
  };

  @Override
  public int add(PrescriptionBean o) {
    final String SQL = "insert prescriptions(prescriptionId, herbId, dosage, usage) values(?, ?, ?, ?)";
    return runRawSQLToUpdate(SQL,
        o.getPrescriptionId(),
        o.getHerbId(),
        o.getDosage(),
        o.getUsage());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from prescriptions where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public PrescriptionBean select(int id) {
    PrescriptionBean p = null;
    final String SQL = "select * from prescriptions where id=?";
    List<PrescriptionBean> ps = runRawSQLToQuery(rm, SQL, id);
    if (ps != null && ps.size() > 0)
      p = ps.get(0);
    return p;
  }

  @Override
  public List<PrescriptionBean> selectAll() {
    final String SQL = "select * from prescriptions";
    return runRawSQLToQuery(rm, SQL);
  }

  @Override
  public int update(PrescriptionBean o) {
    final String SQL = "update prescriptions set prescriptionId=?, herbId=?, dosage=?, usage=? where id=?";
    return runRawSQLToUpdate(SQL,
        o.getPrescriptionId(),
        o.getHerbId(),
        o.getDosage(),
        o.getUsage(),
        o.getId());
  }

  /**
   * 根据用法用量查找处方。
   */
  public List<PrescriptionBean> selectByField(String field) {
    List<PrescriptionBean> ps = new ArrayList<PrescriptionBean>();
    for (PrescriptionBean h : selectAll())
      if (h.getDosage().contains(field)
          || h.getUsage().contains(field))
        ps.add(h);
    return ps;
  }

  /**
   * 根据解释查找元素。
   */
  public List<PrescriptionBean> selectByPrescriptionInfo(String pi) {
    List<PrescriptionBean> hs = new ArrayList<PrescriptionBean>();
    for (PrescriptionInfoBean e : new PrescriptionInfoDAO().selectAll())
      if (e.getDescription().equals(pi))
        hs.add(select(e.getId()));
    return hs;
  }
}
