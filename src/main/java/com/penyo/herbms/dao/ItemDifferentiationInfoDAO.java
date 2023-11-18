package com.penyo.herbms.dao;

import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;

/**
 * 条辨概要的数据访问代理。
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 */
public class ItemDifferentiationInfoDAO extends AbstractDAO<ItemDifferentiationInfoBean> {
  final RowMapper<ItemDifferentiationInfoBean> rm = (rs) -> {
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
  public ItemDifferentiationInfoBean select(int id) {
    ItemDifferentiationInfoBean idti = null;
    final String SQL = "select * from item_differentiation_infos where id=?";
    List<ItemDifferentiationInfoBean> idtis = runRawSQLToQuery(rm, SQL, id);
    if (idtis != null && idtis.size() > 0) idti = idtis.get(0);
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
    return runRawSQLToUpdate(SQL, o.getCode(), o.getContent(), o.getAnnotation(), o.getId());
  }

  /**
   * 根据字段查找元素。
   */
  public List<ItemDifferentiationInfoBean> selectByField(String field) {
    List<ItemDifferentiationInfoBean> idtis = new ArrayList<>();
    for (ItemDifferentiationInfoBean h : selectAll())
      if (h.getAnnotation().contains(field) || h.getContent().contains(field)) idtis.add(h);
    return idtis;
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public ItemDifferentiationInfoBean selectByPrescriptionId(int id) {
    int neededId = -1;

    PrescriptionDAO pDAO = new PrescriptionDAO();
    ItemDifferentiationDAO idtDAO = new ItemDifferentiationDAO();
    for (ItemDifferentiationBean o : idtDAO.selectAll())
      if (o.getPrescriptionId() == pDAO.select(id).getPrescriptionId())
        neededId = idtDAO.select(o.getId()).getItemDifferentionId();

    return select(neededId);
  }
}
