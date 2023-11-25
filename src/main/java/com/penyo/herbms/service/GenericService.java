package com.penyo.herbms.service;

import com.penyo.herbms.dao.*;
import com.penyo.herbms.pojo.GenericBean;

/**
 * 通用业务代理
 *
 * @author Penyo
 * @see com.penyo.herbms.service.AbstractService
 */
public abstract class GenericService<UncertainBean extends GenericBean> implements AbstractService<UncertainBean> {
  protected final HerbDAO hDAO = (HerbDAO) GenericDAO.createDAO("herb");
  protected final ExperienceDAO expDAO = (ExperienceDAO) GenericDAO.createDAO("experience");
  protected final PrescriptionInfoDAO piDAO = (PrescriptionInfoDAO) GenericDAO.createDAO("prescription-info");
  protected final PrescriptionDAO pDAO = (PrescriptionDAO) GenericDAO.createDAO("prescription");
  protected final ItemDifferentiationInfoDAO idtiDAO = (ItemDifferentiationInfoDAO) GenericDAO.createDAO("item-differentiation-info");
  protected final ItemDifferentiationDAO idtDAO = (ItemDifferentiationDAO) GenericDAO.createDAO("item-differentiation");
}
