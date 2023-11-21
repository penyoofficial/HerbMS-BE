package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.util.NeedRebuild;

import java.util.ArrayList;
import java.util.List;

/**
 * 条辩概要的业务层
 *
 * @author lyh
 */
@NeedRebuild
public class ItemDifferentiationInfoService extends AbstractService {
  /**
   * 添加单个元素。
   */
  public int add(ItemDifferentiationInfoBean o) {
    return idtiDAO.add(o);
  }

  /**
   * 根据 ID 删除单个元素。
   */
  public int deleteById(int id) {
    return idtiDAO.delete(id);
  }

  /**
   * 根据 ID 查找单个元素。
   */
  public ItemDifferentiationInfoBean selectById(int id) {
    return idtiDAO.select(id);
  }

  /**
   * 修改单个元素。
   */
  public int update(ItemDifferentiationInfoBean o) {
    return idtiDAO.update(o);
  }

  /**
   * 根据字段查找元素。
   */
  public List<ItemDifferentiationInfoBean> selectByField(String field) {
    if (field.isEmpty()) return idtiDAO.selectAll();

    List<ItemDifferentiationInfoBean> idtis = new ArrayList<>();
    for (ItemDifferentiationInfoBean h : idtiDAO.selectAll())
      if (h.getAnnotation().contains(field) || h.getContent().contains(field)) idtis.add(h);
    return idtis;
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public ItemDifferentiationInfoBean selectByPrescriptionId(int id) {
    int neededId = -1;
    for (ItemDifferentiationBean o : idtDAO.selectAll())
      if (o.getPrescriptionId() == pDAO.select(id).getPrescriptionId())
        neededId = idtDAO.select(o.getId()).getItemDifferentionId();
    return idtiDAO.select(neededId);
  }
}
