package com.penyo.herbms;

import com.penyo.herbms.service.GenericService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 应用程序上下文
 *
 * @author Penyo
 */
@Component("context")
public class HerbMSContext {
  /**
   * 应用程序上下文
   */
  private static ApplicationContext ac = null;

  /**
   * 获取应用程序上下文。
   */
  public static ApplicationContext getApplicationContext() {
    if (ac == null) ac = new ClassPathXmlApplicationContext("herbMSContext.xml");
    return ac;
  }

  /**
   * 获取指定服务。
   */
  public static <UncertainService extends GenericService<?>> UncertainService getService(Class<UncertainService> prototype) {
    return getApplicationContext().getBean(prototype);
  }
}
