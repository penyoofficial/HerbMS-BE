package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ExperienceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 中药的业务层
 *
 * @author Penyo
 */
public class HerbService extends AbstractService<HerbBean> {
  @Override
  public int add(HerbBean o) {
    return hDAO.add(o);
  }

  @Override
  public int deleteById(int id) {
    return hDAO.delete(id);
  }

  @Override
  public int update(HerbBean o) {
    return hDAO.update(o);
  }

  @Override
  public HerbBean selectById(int id) {
    return hDAO.select(id);
  }

  @Override
  public List<HerbBean> selectByFields(String... fields) {
    return hDAO.select(fields);
  }

  /**
   * 根据心得查找元素。
   */
  public List<HerbBean> selectByExperience(String exp) {
    List<HerbBean> hs = new ArrayList<>();
    for (ExperienceBean e : expDAO.selectAll())
      if (e.getContent().equals(exp)) hs.add(hDAO.select(e.getHerbId()));
    return hs;
  }
}
