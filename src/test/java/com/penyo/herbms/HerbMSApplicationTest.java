package com.penyo.herbms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.penyo.herbms.dao.*;
import com.penyo.herbms.pojo.*;

/**
 * 应用程序从这里测试。
 * 
 * @author Penyo
 */
public class HerbMSApplicationTest {
  @Test
  public void testExperienceDAO() throws Exception {
    ExperienceDAO dao = new ExperienceDAO();
    ExperienceBean o = dao.select(1);

    if (o == null)
      throw new Exception();
    assertEquals("《本草纲目》", o.getDerivation());
  }

  @Test
  public void testHerbDAO() throws Exception {
    HerbDAO dao = new HerbDAO();
    HerbBean o = dao.select(1);

    if (o == null)
      throw new Exception();
    assertEquals("人参", o.getName());
  }

  @Test
  public void testItemDifferentiationInfoDAO() throws Exception {
    ItemDifferentiationInfoDAO dao = new ItemDifferentiationInfoDAO();
    ItemDifferentiationInfoBean o = dao.select(1);

    if (o == null)
      throw new Exception();
    assertEquals("补气健脾，生津止渴", o.getContent());
  }

  @Test
  public void testItemDifferentiationDAO() throws Exception {
    ItemDifferentiationDAO dao = new ItemDifferentiationDAO();
    ItemDifferentiationBean o = dao.select(1);

    if (o == null)
      throw new Exception();
    assertEquals("对症", o.getType());
  }

  @Test
  public void testPrescriptionInfoDAO() throws Exception {
    PrescriptionInfoDAO dao = new PrescriptionInfoDAO();
    PrescriptionInfoBean o = dao.select(1);

    if (o == null)
      throw new Exception();
    assertEquals("补气生津方", o.getName());
  }

  @Test
  public void testPrescriptionDAO() throws Exception {
    PrescriptionDAO dao = new PrescriptionDAO();
    PrescriptionBean o = dao.select(1);

    if (o == null)
      throw new Exception();
    assertEquals("3~9g", o.getDosage());
  }
}
