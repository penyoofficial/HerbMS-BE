package com.penyo.herbms.service;

import com.penyo.herbms.pojo.HerbBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 中草药的业务代理
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
   * 根据中草药使用心得 ID 查询单个中草药名称。
   */
  public String selectNameByExperienceId(int id) {
    return hDAO.selectByExperienceId(id).getName();
  }

  /**
   * 根据处方 ID 查找多个中草药名称。
   */
  public List<String> selectNamesByPrescriptionId(int id) {
    List<String> names = new ArrayList<>();

    for (HerbBean o : hDAO.selectByPrescriptionId(id))
      names.add(o.getName());
    return names;
  }
}
