package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.HerbBean;

/**
 * 中药的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 */
public class HerbDAO extends GenericDAO<HerbBean> {
  protected HerbDAO() {
    super("HerbMapper");
  }
}
