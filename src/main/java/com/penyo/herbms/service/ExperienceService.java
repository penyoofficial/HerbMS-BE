package com.penyo.herbms.service;

import com.penyo.herbms.pojo.ExperienceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 中药使用心得的业务代理
 *
 * @author Penyo
 */
public class ExperienceService extends GenericService<ExperienceBean> {
  @Override
  public int add(ExperienceBean o) {
    return expDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return expDAO.delete(id);
  }

  @Override
  public int update(ExperienceBean o) {
    return expDAO.update(o);
  }

  @Override
  public ExperienceBean selectById(int id) {
    return expDAO.selectById(id);
  }

  @Override
  public List<ExperienceBean> selectByFields(List<String> fields) {
    return expDAO.selectByFields(fields);
  }

  /**
   * 根据中药 ID 查找元素。
   */
  public List<String> selectContentsByHerbId(int id) {
    List<String> contents = new ArrayList<>();

    for (ExperienceBean h : expDAO.selectByHerbId(id))
      contents.add(h.getContent());
    return contents;
  }
}
