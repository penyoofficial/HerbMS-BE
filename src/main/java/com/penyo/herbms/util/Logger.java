package com.penyo.herbms.util;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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

  /**
   * 监听出现异常的业务层全部方法并输出异常信息
   */
  @AfterThrowing(value = "execution(* com.penyo.herbms.service.*.*(..))",throwing = "exception")
  public void ListenQueryException(JoinPoint joinPoint,Throwable exception){
    LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 捕获 " + exception.toString());
  }

  /**
   * 无论业务层中方法是否发生异常，输出必要信息
   */
  @Around(value = "execution(* com.penyo.herbms.service.*.*(..))")
  public Object ListenQueryAlways(ProceedingJoinPoint joinPoint){
    Object result = null;
    try {
      result = joinPoint.proceed();
    }catch (Throwable throwable){
      LOGGER.info("\n\t- 捕获 " + throwable);
    }finally {
      LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 参数:" + Arrays.toString(joinPoint.getArgs()));
    }
    return result;
  }

  /**
   * 在业务层方法执行前输出必要信息
   */
  @Before(value = "execution(* com.penyo.herbms.service.*.*(..))")
  public void listenQuery(JoinPoint joinPoint) {
    LOGGER.info("\n\t- 来自 " + joinPoint.getSignature().toLongString() + "\n\t- 参数:" + Arrays.toString(joinPoint.getArgs()));
  }

  /**
   * 输出业务层所有查询方法的执行时间
   */
  @Around(value = "execution(* com.penyo.herbms.service.*.select*(..))")
  public Object ListenQueryExecutionTime(ProceedingJoinPoint joinPoint){
    long start = System.currentTimeMillis();
    LOGGER.info("\n\t- 来自 " + joinPoint.getTarget().getClass().getName() + joinPoint.getSignature());
    LOGGER.info("\n\t- 方法开始时间:"+start);
    Object result = null;
    try {
      result = joinPoint.proceed();
    }catch (Throwable throwable){
      LOGGER.info("\n\t- 捕获 " + throwable);
    }finally {
      long end = System.currentTimeMillis();
      LOGGER.info("\n\t- 方法结束时间:"+end + "\n\t- 总耗时:" + (end-start) + "ms");
    }
    return result;
  }
}