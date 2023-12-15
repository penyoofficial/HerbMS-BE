package com.penyo.herbms.util;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 全局日志发生器
 *
 * @author Penyo
 */
@Component
@Aspect
public class Logger {
  /**
   * 公共日志发生器
   */
  private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class);

  /**
   * 监听业务层全部方法的返回值并输出。
   */
  @AfterReturning(value = "execution(* com.penyo.herbms.service.*.select*(..))", returning = "result")
  public void listenQueryResult(JoinPoint joinPoint, Object result) {
    LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 捕获 " + result.toString());
  }
}