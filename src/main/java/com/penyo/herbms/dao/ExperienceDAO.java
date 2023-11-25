package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.ExperienceBean;

/**
 * 中药使用心得的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
public class ExperienceDAO extends GenericDAO<ExperienceBean> {
  protected ExperienceDAO() {
    super("ExperienceMapper");
  }
}
