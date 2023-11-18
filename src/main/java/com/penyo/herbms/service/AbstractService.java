package com.penyo.herbms.service;

import com.penyo.herbms.dao.*;

/**
 * 业务层。
 *
 * @author Penyo
 */
public abstract class AbstractService {
  protected final HerbDAO hDAO = (HerbDAO) AbstractDAO.createDAO("herb");
  protected final ExperienceDAO expDAO = (ExperienceDAO) AbstractDAO.createDAO("experience");
  protected final PrescriptionInfoDAO piDAO = (PrescriptionInfoDAO) AbstractDAO.createDAO("prescription-info");
  protected final PrescriptionDAO pDAO = (PrescriptionDAO) AbstractDAO.createDAO("prescription");
  protected final ItemDifferentiationInfoDAO idtiDAO = (ItemDifferentiationInfoDAO) AbstractDAO.createDAO("item-differentiation-info");
  protected final ItemDifferentiationDAO idtDAO = (ItemDifferentiationDAO) AbstractDAO.createDAO("item-differentiation");
}
