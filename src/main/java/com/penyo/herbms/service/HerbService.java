package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ExperienceBean;

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
   * 根据心得查找元素。
   */
  public List<HerbBean> selectByExperience(String exp) {
    List<HerbBean> hs = new ArrayList<>();
    for (ExperienceBean e : expDAO.selectAll())
      if (e.getContent().equals(exp)) hs.add(hDAO.selectById(e.getHerbId()));
    return hs;
  }
}
