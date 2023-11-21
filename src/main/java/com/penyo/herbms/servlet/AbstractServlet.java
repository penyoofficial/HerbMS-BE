package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.AbstractBean;
import com.penyo.herbms.service.AbstractService;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 请求处理层
 *
 * @author Penyo
 */
public abstract class AbstractServlet<UncertainBeanA extends AbstractBean, UncertainBeanB extends AbstractBean, UncertainServiceA extends AbstractService<UncertainBeanA>, UncertainServiceB extends AbstractService<UncertainBeanB>> extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    doPost(req, resp);
  }

  /**
   * 处理请求。
   */
  protected void doProcess(HttpServletRequest req, UncertainServiceA serviceA, UncertainServiceB serviceB) {
    List<UncertainBeanA> objsA = new ArrayList<>();
    List<UncertainBeanB> objsB = new ArrayList<>();

    Enumeration<String> paramNames = req.getParameterNames();
    Map<String, String> params = new HashMap<>();
    while (paramNames.hasMoreElements()) {
      String key = paramNames.nextElement();
      params.put(key, req.getParameter(key));
    }

    // Query Part

    String keyword = params.get("keyword");
    if (keyword == null) keyword = "";

    boolean isId = false;
    String oIsId = params.get("isId");
    if (oIsId != null) isId = oIsId.equals("on");

    boolean needQueryA = true;
    String oNeedQueryA = params.get("needQueryA");
    if (oNeedQueryA != null) needQueryA = oNeedQueryA.equals("1");

    // Update Part

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (opType == null) {
          if (needQueryA) {
            UncertainBeanA obj = getAInstance(params);
            if (serviceA.selectById(id) == null) serviceA.add(obj);
            else serviceA.update(obj);
          } else {
            UncertainBeanB obj = getBInstance(params);
            if (serviceB.selectById(id) == null) serviceB.add(obj);
            else serviceB.update(obj);
          }
        } else if (opType.equals("delete")) {
          if (needQueryA) serviceA.deleteById(id);
          else serviceB.deleteById(id);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Arbitrate Time

    if (isId) {
      if (needQueryA) objsA.add(serviceA.selectById(Integer.parseInt(keyword)));
      else objsB.add(serviceB.selectById(Integer.parseInt(keyword)));
    } else {
      if (needQueryA) objsA = serviceA.selectByField(keyword);
      else objsB = serviceB.selectByField(keyword);
    }

    // Transport Time

    req.getSession().setAttribute("needQueryA", needQueryA);
    req.getSession().setAttribute("list", needQueryA ? objsA : objsB);
  }

  /**
   * 从参数图中获取值并构造数据容器。
   */
  protected abstract UncertainBeanA getAInstance(Map<String, String> params);

  /**
   * 从参数图中获取值并构造数据容器。
   */
  protected abstract UncertainBeanB getBInstance(Map<String, String> params);
}
