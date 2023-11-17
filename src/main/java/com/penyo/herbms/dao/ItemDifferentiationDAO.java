package com.penyo.herbms.dao;

import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.pojo.ItemDifferentiationBean;

/**
 * 条辨的持久层。
 * 
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
public class ItemDifferentiationDAO extends DAO<ItemDifferentiationBean> {
  RowMapper<ItemDifferentiationBean> rm = (rs) -> {
    ItemDifferentiationBean idt = null;
    try {
      idt = new ItemDifferentiationBean(
          rs.getInt("id"),
          rs.getInt("itemDifferentionId"),
          rs.getInt("prescriptionId"),
          rs.getString("type"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return idt;
  };

  @Override
  public int add(ItemDifferentiationBean o) {
    final String SQL = "insert item_differentiations(itemDifferentionId, prescriptionId, type) values(?, ?, ?)";
    return runRawSQLToUpdate(SQL,
        o.getItemDifferentionId(),
        o.getPrescriptionId(),
        o.getType());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from item_differentiations where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public ItemDifferentiationBean select(int id) {
    ItemDifferentiationBean idt = null;
    final String SQL = "select * from item_differentiations where id=?";
    List<ItemDifferentiationBean> idts = runRawSQLToQuery(rm, SQL, id);
    if (idts != null && idts.size() > 0)
      idt = idts.get(0);
    return idt;
  }

  @Override
  public List<ItemDifferentiationBean> selectAll() {
    final String SQL = "select * from item_differentiations";
    return runRawSQLToQuery(rm, SQL);
  }

  @Override
  public int update(ItemDifferentiationBean o) {
    final String SQL = "update item_differentiations set itemDifferentionId=?, prescriptionId=?, type=? where id=?";
    return runRawSQLToUpdate(SQL,
        o.getItemDifferentionId(),
        o.getPrescriptionId(),
        o.getType(),
        o.getId());
  }

  /**
   * 根据字段查找元素。
   */
  public List<ItemDifferentiationBean> selectByField(String field) {
    List<ItemDifferentiationBean> idts = new ArrayList<ItemDifferentiationBean>();
    for (ItemDifferentiationBean h : selectAll())
      if (h.getType().contains(field))
        idts.add(h);
    return idts;
  }
}
