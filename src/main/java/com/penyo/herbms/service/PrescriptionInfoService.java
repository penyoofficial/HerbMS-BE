package com.penyo.herbms.service;

import com.penyo.herbms.pojo.PrescriptionInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 经方概要的业务层
 *
 * @author hawkkie
 */
public class PrescriptionInfoService extends AbstractService {
  /**
   * 添加单个元素。
   */
  public int add(PrescriptionInfoBean o) {
    return piDAO.add(o);
  }

  /**
   * 根据 ID 删除单个元素。
   */
  public int deleteById(int id) {
    return piDAO.delete(id);
  }

  /**
   * 根据 ID 查找单个元素。
   */
  public PrescriptionInfoBean selectById(int id) {
    return piDAO.select(id);
  }

  /**
   * 修改单个元素。
   */
  public int update(PrescriptionInfoBean o) {
    return piDAO.update(o);
  }

  /**
   * 根据字段查找元素。
   */
  public List<PrescriptionInfoBean> selectByField(String field) {
    List<PrescriptionInfoBean> pis = new ArrayList<>();
    for (PrescriptionInfoBean h : piDAO.selectAll())
      if (h.getName().contains(field) || h.getNickname().contains(field) || h.getDescription().contains(field))
        pis.add(h);
    return pis;
  }
}
