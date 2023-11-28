package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.pojo.PrescriptionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 中药的业务代理
 *
 * @author Penyo
 */
public class HerbService extends GenericService<HerbBean> {
  @Override
  public int add(HerbBean o) {
    return hDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return hDAO.delete(id);
  }

  @Override
  public int update(HerbBean o) {
    return hDAO.update(o);
  }

  @Override
  public HerbBean selectById(int id) {
    return hDAO.selectById(id);
  }

  @Override
  public List<HerbBean> selectByFields(List<String> fields) {
    return hDAO.selectByFields(fields);
  }

  /**
   * 根据心得 ID 查找元素。
   */
  public HerbBean selectByExperienceId(int id) {
    for (ExperienceBean o : expDAO.selectAll())
      if (o.getId() == id) return hDAO.selectById(o.getHerbId());
    return null;
  }

  /**
   * 根据处方 ID 查找元素。
   */
  public List<HerbBean> selectByPrescriptionId(int id) {
    List<HerbBean> hs = new ArrayList<>();
    for (PrescriptionBean o : pDAO.selectAll())
      if (o.getId() == id) hs.add(hDAO.selectById(o.getHerbId()));
    return hs;
  }
}
