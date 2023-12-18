package com.penyo.herbms.controller;

import com.penyo.herbms.pojo.GenericBean;
import com.penyo.herbms.util.AppConfig;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 抽象控制器代理
 *
 * @author Penyo
 */
public interface AbstractController<UnknownBean extends GenericBean> {
  /**
   * 将请求参数转化为图。
   */
  default Map<String, String> toMap(HttpServletRequest request) {
    Map<String, String> params = new HashMap<>();

    Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String key = paramNames.nextElement();
      params.put(key, request.getParameter(key));
    }

    return params;
  }

  /**
   * 获取已被增强的业务对象。
   */
  default <UnknownService> UnknownService getService(Class<UnknownService> def) {
    return new AnnotationConfigApplicationContext(AppConfig.class).getBean(def);
  }

  /**
   * 处理主要请求。
   */
  String requestMain(HttpServletRequest request);

  /**
   * 处理次要请求。
   */
  String requestSub(HttpServletRequest request);

  /**
   * 从参数图中获取值并构造数据容器。
   */
  UnknownBean getInstance(Map<String, String> params);
}
