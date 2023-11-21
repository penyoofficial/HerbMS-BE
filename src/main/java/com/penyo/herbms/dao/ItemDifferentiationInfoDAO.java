package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;

import java.util.List;

/**
 * 条辨概要的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 */
public class ItemDifferentiationInfoDAO extends AbstractDAO<ItemDifferentiationInfoBean> {
  protected final RowMapper<ItemDifferentiationInfoBean> rm = (rs) -> {
    ItemDifferentiationInfoBean idti = null;
    try {
      idti = new ItemDifferentiationInfoBean(rs.getInt("id"), rs.getInt("code"), rs.getString("content"), rs.getString("annotation"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return idti;
  };

  protected ItemDifferentiationInfoDAO() {
  }

  @Override
  public int add(ItemDifferentiationInfoBean o) {
    final String SQL = "insert item_differentiation_infos(code, content, annotation) values(?, ?, ?)";
    return runRawSQLToUpdate(SQL, o.getCode(), o.getContent(), o.getAnnotation());
  }

  @Override
  public int delete(int id) {
    final String SQL = "delete from item_differentiation_infos where id=?";
    return runRawSQLToUpdate(SQL, id);
  }

  @Override
  public int update(ItemDifferentiationInfoBean o) {
    final String SQL = "update item_differentiation_infos set code=?, content=?, annotation=? where id=?";
    return runRawSQLToUpdate(SQL, o.getCode(), o.getContent(), o.getAnnotation(), o.getId());
  }

  @Override
  public ItemDifferentiationInfoBean select(int id) {
    ItemDifferentiationInfoBean idti = null;
    final String SQL = "select * from item_differentiation_infos where id=?";
    List<ItemDifferentiationInfoBean> idtis = runRawSQLToQuery(rm, SQL, id);
    if (idtis != null && !idtis.isEmpty()) idti = idtis.get(0);
    return idti;
  }

  @Override
  public List<ItemDifferentiationInfoBean> select(String... fields) {
    if (fields.length == 1 && fields[0].isEmpty()) return selectAll();

    final String SQL = "select * from item_differentiation_infos where " + "concat(content, annotation) like concat('%', ?, '%') and ".repeat(fields.length);
    return runRawSQLToQuery(rm, SQL.substring(0, SQL.length() - 5), (Object[]) fields);
  }

  @Override
  public List<ItemDifferentiationInfoBean> selectAll() {
    final String SQL = "select * from item_differentiation_infos";
    return runRawSQLToQuery(rm, SQL);
  }
}
