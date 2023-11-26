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
  protected final HerbDAO hDAO = (HerbDAO) GenericDAO.createDAO(GenericDAO.DAOType.HERB);
  protected final ExperienceDAO expDAO = (ExperienceDAO) GenericDAO.createDAO(GenericDAO.DAOType.EXPERIENCE);
  protected final PrescriptionInfoDAO piDAO = (PrescriptionInfoDAO) GenericDAO.createDAO(GenericDAO.DAOType.PRESCRIPTION_INFO);
  protected final PrescriptionDAO pDAO = (PrescriptionDAO) GenericDAO.createDAO(GenericDAO.DAOType.PRESCRIPTION);
  protected final ItemDifferentiationInfoDAO idtiDAO = (ItemDifferentiationInfoDAO) GenericDAO.createDAO(GenericDAO.DAOType.ITEM_DIFFERENTIATION_INFO);
  protected final ItemDifferentiationDAO idtDAO = (ItemDifferentiationDAO) GenericDAO.createDAO(GenericDAO.DAOType.ITEM_DIFFERENTIATION);
}
