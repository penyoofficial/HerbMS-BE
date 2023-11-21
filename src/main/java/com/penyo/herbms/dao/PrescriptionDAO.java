package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.PrescriptionBean;

import java.util.List;

/**
 * 处方的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
public class PrescriptionDAO extends AbstractDAO<PrescriptionBean> {
  protected final RowMapper<PrescriptionBean> rm = (rs) -> {
    PrescriptionBean p = null;
    try {
      p = new PrescriptionBean(rs.getInt("id"), rs.getInt("prescriptionId"), rs.getInt("herbId"), rs.getString("dosage"), rs.getString("usage"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return p;
  };

  protected PrescriptionDAO() {
  }

  @Override
  public int add(PrescriptionBean o) {
    final String SQL = "insert prescriptions(prescriptionId, herbId, dosage, usage) values(?, ?, ?, ?)";
    return runRawSQLToUpdate(SQL, o.getPrescriptionId(), o.getHerbId(), o.getDosage(), o.getUsage());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from prescriptions where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public int update(PrescriptionBean o) {
    final String SQL = "update prescriptions set prescriptionId=?, herbId=?, dosage=?, `usage`=? where id=?";
    return runRawSQLToUpdate(SQL, o.getPrescriptionId(), o.getHerbId(), o.getDosage(), o.getUsage(), o.getId());
  }

  @Override
  public PrescriptionBean select(int id) {
    PrescriptionBean p = null;
    final String SQL = "select * from prescriptions where id=?";
    List<PrescriptionBean> ps = runRawSQLToQuery(rm, SQL, id);
    if (ps != null && !ps.isEmpty()) p = ps.get(0);
    return p;
  }

  @Override
  public List<PrescriptionBean> select(String... fields) {
    if (fields.length == 0) return selectAll();

    StringBuilder tempSQL = new StringBuilder("select * from prescriptions where ");
    for (int i = 0; i < fields.length; i++)
      tempSQL.append("concat_ws(dosage, usage) like '%?%' or ");
    final String SQL = tempSQL.toString();
    return runRawSQLToQuery(rm, SQL.substring(0, SQL.length() - 4), (Object) fields);
  }

  @Override
  public List<PrescriptionBean> selectAll() {
    final String SQL = "select * from prescriptions";
    return runRawSQLToQuery(rm, SQL);
  }
}
