package com.penyo.herbms.util;

import com.penyo.herbms.dao.HerbDAO;

/**
 * 启动过程封装器。
 * 
 * @author Penyo
 */
public class Bootloader {
  /**
   * 启动应用程序。
   */
  public static void boot(String version) {
    HerbDAO hDAO = new HerbDAO();
    System.out.println(hDAO.selectAll());
  }

  /**
   * 关闭应用程序。
   */
  public static void shutdown() {
    // nothing here
  }
}
