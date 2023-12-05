package com.penyo.herbms.service;

import com.penyo.herbms.dao.*;
import com.penyo.herbms.pojo.GenericBean;
import com.penyo.herbms.util.TableMapper;

/**
 * 通用业务代理
 *
 * @author Penyo
 * @see com.penyo.herbms.service.AbstractService
 */
public abstract class GenericService<UncertainBean extends GenericBean> implements AbstractService<UncertainBean> {
  protected final HerbDAO hDAO = (HerbDAO) GenericDAO.createDAO(TableMapper.HERBS);
  protected final ExperienceDAO expDAO = (ExperienceDAO) GenericDAO.createDAO(TableMapper.EXPERIENCES);
  protected final PrescriptionInfoDAO piDAO = (PrescriptionInfoDAO) GenericDAO.createDAO(TableMapper.PRESCRIPTION_INFOS);
  protected final PrescriptionDAO pDAO = (PrescriptionDAO) GenericDAO.createDAO(TableMapper.PRESCRIPTIONS);
  protected final ItemDifferentiationInfoDAO idtiDAO = (ItemDifferentiationInfoDAO) GenericDAO.createDAO(TableMapper.ITEM_DIFFERENTIATION_INFOS);
  protected final ItemDifferentiationDAO idtDAO = (ItemDifferentiationDAO) GenericDAO.createDAO(TableMapper.ITEM_DIFFERENTIATIONS);
}
