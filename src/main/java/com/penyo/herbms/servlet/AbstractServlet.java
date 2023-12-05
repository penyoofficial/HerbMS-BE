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
public interface AbstractServlet<UncertainBean extends GenericBean, UncertainService extends GenericService<UncertainBean>> {
  /**
   * 处理请求并决定是否烧录输出流。
   */
  ReturnDataPack<UncertainBean> doProcess(HttpServletRequest req, HttpServletResponse resp, UncertainService serv, boolean needBurn);

  /**
   * 处理特殊请求。
   */
  void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, UncertainService serv);

  /**
   * 以 JSON 响应请求。
   */
  void doResponseInJSON(HttpServletResponse resp, ReturnDataPack<?> obj);

  /**
   * 从参数图中获取值并构造数据容器。
   */
  UncertainBean getInstance();
}
