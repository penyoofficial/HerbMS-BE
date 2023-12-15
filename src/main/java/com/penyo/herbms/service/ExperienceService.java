package com.penyo.herbms.service;

import com.penyo.herbms.pojo.Experience;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 中草药使用心得的业务代理
 *
 * @author Penyo
 */
@Service
public class ExperienceService extends GenericService<Experience> {
  @Override
  public int add(Experience o) {
    return experienceDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return experienceDAO.delete(id);
  }

  @Override
  public int update(Experience o) {
    return experienceDAO.update(o);
  }

  @Override
  public Experience selectById(int id) {
    return experienceDAO.selectById(id);
  }

  @Override
  public List<Experience> selectByFields(List<String> fields) {
    return experienceDAO.selectByFields(fields);
  }

  /**
   * 根据中草药 ID 查找多个中草药使用心得内容。
   */
  public List<String> selectContentsByHerbId(int id) {
    List<String> contents = new ArrayList<>();

    for (Experience h : experienceDAO.selectByHerbId(id))
      contents.add(h.getContent());
    return contents;
  }
}
