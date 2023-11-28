package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.GenericBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.GenericService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 抽象请求处理代理
 *
 * @author Penyo
 */
public interface AbstractServlet<UncertainBeanA extends GenericBean, UncertainBeanB extends GenericBean, UncertainServiceA extends GenericService<UncertainBeanA>, UncertainServiceB extends GenericService<UncertainBeanB>> {
  /**
   * 处理请求并决定是否烧录输出流。
   */
  ReturnDataPack<? extends GenericBean> doProcess(HttpServletRequest req, HttpServletResponse resp, UncertainServiceA serviceA, UncertainServiceB serviceB, boolean needBurn);

  /**
   * 处理特殊请求。
   */
  void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, UncertainServiceA serviceA, UncertainServiceB serviceB);

  /**
   * 以 JSON 响应请求。
   */
  void doResponseInJSON(HttpServletResponse resp, ReturnDataPack<?> obj);

  /**
   * 从参数图中获取值并构造 A 型数据容器。
   */
  UncertainBeanA getAInstance();

  /**
   * 从参数图中获取值并构造 B 型数据容器。
   */
  UncertainBeanB getBInstance();
}
