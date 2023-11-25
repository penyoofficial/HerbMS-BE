package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.PrescriptionBean;

/**
 * 处方的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
public class PrescriptionDAO extends GenericDAO<PrescriptionBean> {
  protected PrescriptionDAO() {
    super("PrescriptionMapper");
  }
}
