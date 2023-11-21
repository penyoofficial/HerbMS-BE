package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiationBean;

import java.util.List;

/**
 * 条辨的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
public class ItemDifferentiationDAO extends AbstractDAO<ItemDifferentiationBean> {
  protected final RowMapper<ItemDifferentiationBean> rm = (rs) -> {
    ItemDifferentiationBean idt = null;
    try {
      idt = new ItemDifferentiationBean(rs.getInt("id"), rs.getInt("itemDifferentionId"), rs.getInt("prescriptionId"), rs.getString("type"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return idt;
  };

  protected ItemDifferentiationDAO() {
  }

  @Override
  public int add(ItemDifferentiationBean o) {
    final String SQL = "insert item_differentiations(itemDifferentionId, prescriptionId, type) values(?, ?, ?)";
    return runRawSQLToUpdate(SQL, o.getItemDifferentionId(), o.getPrescriptionId(), o.getType());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from item_differentiations where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public int update(ItemDifferentiationBean o) {
    final String SQL = "update item_differentiations set itemDifferentionId=?, prescriptionId=?, type=? where id=?";
    return runRawSQLToUpdate(SQL, o.getItemDifferentionId(), o.getPrescriptionId(), o.getType(), o.getId());
  }

  @Override
  public ItemDifferentiationBean select(int id) {
    ItemDifferentiationBean idt = null;
    final String SQL = "select * from item_differentiations where id=?";
    List<ItemDifferentiationBean> idts = runRawSQLToQuery(rm, SQL, id);
    if (idts != null && !idts.isEmpty()) idt = idts.get(0);
    return idt;
  }

  @Override
  public List<ItemDifferentiationBean> select(String... fields) {
    if (fields.length == 0) return selectAll();

    StringBuilder tempSQL = new StringBuilder("select * from item_differentiations where ");
    for (int i = 0; i < fields.length; i++)
      tempSQL.append("concat_ws(type) like '%?%' or ");
    final String SQL = tempSQL.toString();
    return runRawSQLToQuery(rm, SQL.substring(0, SQL.length() - 4), (Object) fields);
  }

  @Override
  public List<ItemDifferentiationBean> selectAll() {
    final String SQL = "select * from item_differentiations";
    return runRawSQLToQuery(rm, SQL);
  }
}
