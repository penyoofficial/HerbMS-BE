package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.PrescriptionInfoBean;

/**
 * 处方概要的数据访问代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 */
public class PrescriptionInfoDAO extends GenericDAO<PrescriptionInfoBean> {
  protected PrescriptionInfoDAO() {
    super("PrescriptionInfoMapper");
  }
}
