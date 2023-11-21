package com.penyo.herbms.service;

import com.penyo.herbms.pojo.PrescriptionBean;
import com.penyo.herbms.util.NeedRebuild;

import java.util.ArrayList;
import java.util.List;

/**
 * 经方的业务层
 *
 * @author hawkkie
 */
@NeedRebuild
public class PrescriptionService extends AbstractService {
  /**
   * 添加单个元素。
   */
  public int add(PrescriptionBean o) {
    return pDAO.add(o);
  }

  /**
   * 根据 ID 删除单个元素。
   */
  public int deleteById(int id) {
    return pDAO.delete(id);
  }

  /**
   * 根据 ID 查找单个元素。
   */
  public PrescriptionBean selectById(int id) {
    return pDAO.select(id);
  }

  /**
   * 修改单个元素。
   */
  public int update(PrescriptionBean o) {
    return pDAO.update(o);
  }

  /**
   * 根据字段查找元素。
   */
  public List<PrescriptionBean> selectByField(String field) {
    List<PrescriptionBean> ps = new ArrayList<>();
    for (PrescriptionBean h : pDAO.selectAll())
      if (h.getDosage().contains(field) || h.getUsage().contains(field)) ps.add(h);
    return ps;
  }
}
