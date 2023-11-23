package com.penyo.herbms.service;

import com.penyo.herbms.dao.*;
import com.penyo.herbms.pojo.JSONableBean;

import java.util.List;

/**
 * 业务层
 *
 * @author Penyo
 */
public abstract class AbstractService<UncertainBean extends JSONableBean> {
  protected final HerbDAO hDAO = (HerbDAO) AbstractDAO.createDAO("herb");
  protected final ExperienceDAO expDAO = (ExperienceDAO) AbstractDAO.createDAO("experience");
  protected final PrescriptionInfoDAO piDAO = (PrescriptionInfoDAO) AbstractDAO.createDAO("prescription-info");
  protected final PrescriptionDAO pDAO = (PrescriptionDAO) AbstractDAO.createDAO("prescription");
  protected final ItemDifferentiationInfoDAO idtiDAO = (ItemDifferentiationInfoDAO) AbstractDAO.createDAO("item-differentiation-info");
  protected final ItemDifferentiationDAO idtDAO = (ItemDifferentiationDAO) AbstractDAO.createDAO("item-differentiation");

  /**
   * 添加单个元素。
   */
  public abstract int add(UncertainBean o);

  /**
   * 根据 ID 删除单个元素。
   */
  public abstract int deleteById(int id);

  /**
   * 修改单个元素。
   */
  public abstract int update(UncertainBean o);

  /**
   * 根据 ID 查找单个元素。
   */
  public abstract UncertainBean selectById(int id);

  /**
   * 根据字段查找元素。
   */
  public abstract List<UncertainBean> selectByFields(String... fields);
}
