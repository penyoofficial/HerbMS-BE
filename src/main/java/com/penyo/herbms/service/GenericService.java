package com.penyo.herbms.service;

import com.penyo.herbms.dao.*;
import com.penyo.herbms.pojo.GenericBean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用业务代理
 *
 * @author Penyo
 */
public abstract class GenericService<UncertainBean extends GenericBean> implements AbstractService<UncertainBean> {
  @Autowired
  protected HerbDAO herbDAO;
  @Autowired
  protected ExperienceDAO experienceDAO;
  @Autowired
  protected PrescriptionInfoDAO prescriptionInfoDAO;
  @Autowired
  protected PrescriptionDAO prescriptionDAO;
  @Autowired
  protected ItemDifferentiationInfoDAO itemDifferentiationInfoDAO;
  @Autowired
  protected ItemDifferentiationDAO itemDifferentiationDAO;
}
