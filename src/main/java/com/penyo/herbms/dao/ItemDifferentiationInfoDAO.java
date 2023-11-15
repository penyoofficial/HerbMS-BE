package com.penyo.herbms.dao;

import java.util.List;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;

/**
 * 条辨概要的持久层。
 * 
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 */
public class ItemDifferentiationInfoDAO extends DAO<ItemDifferentiationInfoBean> {
  RowMapper<ItemDifferentiationInfoBean> rm = (rs) -> {
    ItemDifferentiationInfoBean idti = null;
    try {
      idti = new ItemDifferentiationInfoBean(
          rs.getInt("id"),
          rs.getInt("code"),
          rs.getString("content"),
          rs.getString("annotation"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return idti;
  };

  @Override
  public int add(ItemDifferentiationInfoBean o) {
    final String SQL = "insert item_differentiation_infos(code, content, annotation) values(?, ?, ?)";
    return runRawSQLToUpdate(SQL,
        o.getCode(),
        o.getContent(),
        o.getAnnotation());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from item_differentiation_infos where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public ItemDifferentiationInfoBean select(int id) {
    ItemDifferentiationInfoBean idti = null;
    final String SQL = "select * from item_differentiation_infos where id=?";
    List<ItemDifferentiationInfoBean> idtis = runRawSQLToQuery(rm, SQL, id);
    if (idtis != null && idtis.size() > 0)
      idti = idtis.get(0);
    return idti;
  }

  @Override
  public List<ItemDifferentiationInfoBean> selectAll() {
    final String SQL = "select * from item_differentiation_infos";
    return runRawSQLToQuery(rm, SQL);
  }

  @Override
  public int update(ItemDifferentiationInfoBean o) {
    final String SQL = "update item_differentiation_infos set code=?, content=?, annotation=? where id=?";
    return runRawSQLToUpdate(SQL,
        o.getCode(),
        o.getContent(),
        o.getAnnotation(),
        o.getId());
  }
}
