package com.penyo.herbms.util;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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
   * 监听业务层全部查询的返回值并输出。
   */
  @AfterReturning(value = "execution(* com.penyo.herbms.service.*.select*(..))", returning = "result")
  public void listenQueryResult(JoinPoint joinPoint, Object result) {
    LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 捕获 " + result.toString());
  }

  /**
   * 监听业务层全部查询的异常并输出。
   */
  @AfterThrowing(value = "execution(* com.penyo.herbms.service.*.*(..))", throwing = "exception")
  public void listenQueryException(JoinPoint joinPoint, Throwable exception) {
    LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 捕获 " + exception.toString());
  }

  /**
   * 监听业务层全部查询的必要信息并输出。
   */
  @Around(value = "execution(* com.penyo.herbms.service.*.*(..))")
  public Object listenQueryAlways(ProceedingJoinPoint joinPoint) {
    Object result = null;
    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      LOGGER.info("\n\t- 捕获 " + throwable);
    } finally {
      LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 参数 " + Arrays.toString(joinPoint.getArgs()));
    }
    return result;
  }

  /**
   * 监听业务层全部查询的参数并输出。
   */
  @Before(value = "execution(* com.penyo.herbms.service.*.*(..))")
  public void listenQuery(JoinPoint joinPoint) {
    LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 参数 " + Arrays.toString(joinPoint.getArgs()));
  }

  /**
   * 监听业务层全部查询的耗时并输出。
   */
  @Around(value = "execution(* com.penyo.herbms.service.*.select*(..))")
  public Object listenQueryExecutionTime(ProceedingJoinPoint joinPoint) {
    long start = System.currentTimeMillis();
    LOGGER.info("\n\t- 来自 " + joinPoint.getTarget().getClass().getName() + joinPoint.getSignature());
    LOGGER.info("\n\t- 方法开始时间 " + start);
    Object result = null;
    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      LOGGER.info("\n\t- 捕获 " + throwable);
    } finally {
      long end = System.currentTimeMillis();
      LOGGER.info("\n\t- 方法结束时间 " + end + "\n\t- 总耗时 " + (end - start) + "ms");
    }
    return result;
  }
}