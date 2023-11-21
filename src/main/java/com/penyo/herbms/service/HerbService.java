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
public class HerbService extends AbstractService {
  /**
   * 添加单个元素。
   */
  public int add(HerbBean o) {
    return hDAO.add(o);
  }

  /**
   * 根据 ID 删除单个元素。
   */
  public int deleteById(int id) {
    return hDAO.delete(id);
  }

  /**
   * 根据 ID 查找单个元素。
   */
  public HerbBean selectById(int id) {
    return hDAO.select(id);
  }

  /**
   * 修改单个元素。
   */
  public int update(HerbBean o) {
    return hDAO.update(o);
  }

  /**
   * 根据字段查找元素。
   */
  public List<HerbBean> selectByField(String field) {
    if (field.isEmpty()) return hDAO.selectAll();

    List<HerbBean> hs = new ArrayList<>();
    for (HerbBean h : hDAO.selectAll())
      if (h.getName().contains(field) || h.getNickname().contains(field) || h.getType().contains(field) || h.getDescription().contains(field) || h.getEfficacy().contains(field) || h.getTaste().contains(field) || h.getOrigin().contains(field) || h.getDosage().contains(field) || h.getTaboo().contains(field) || h.getProcessing().contains(field))
        hs.add(h);
    return hs;
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
