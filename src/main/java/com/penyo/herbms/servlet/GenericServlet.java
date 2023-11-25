package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.GenericBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.GenericService;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 通用请求处理代理
 *
 * @author Penyo
 */
public abstract class GenericServlet<UncertainBeanA extends GenericBean, UncertainBeanB extends GenericBean, UncertainServiceA extends GenericService<UncertainBeanA>, UncertainServiceB extends GenericService<UncertainBeanB>> extends HttpServlet {
  /**
   * 请求参数图
   */
  protected final Map<String, String> params = new HashMap<>();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    doGet(req, resp);
  }

  /**
   * 处理请求。
   */
  protected void doProcess(HttpServletRequest req, HttpServletResponse resp, UncertainServiceA serviceA, UncertainServiceB serviceB) {
    List<UncertainBeanA> objsA = new ArrayList<>();
    List<UncertainBeanB> objsB = new ArrayList<>();

    Enumeration<String> paramNames = req.getParameterNames();
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
    if (oNeedQueryA != null) needQueryA = oNeedQueryA.equals("true");

    // Operate Part

    int affectedRows = -1;

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (needQueryA) {
          if (opType.equals("delete")) {
            affectedRows = serviceA.delete(id);
          } else {
            UncertainBeanA objA = getAInstance();
            if (opType.equals("add")) affectedRows = serviceA.add(objA);
            else affectedRows = serviceA.update(objA);
          }
        } else {
          if (opType.equals("delete")) {
            affectedRows = serviceB.delete(id);
          } else {
            UncertainBeanB objB = getBInstance();
            if (opType.equals("add")) affectedRows = serviceB.add(objB);
            else affectedRows = serviceB.update(objB);
          }
        }
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }

    // Arbitrate Time

    if (isId) {
      if (needQueryA) objsA.add(serviceA.selectById(Integer.parseInt(keyword)));
      else objsB.add(serviceB.selectById(Integer.parseInt(keyword)));
    } else {
      if (needQueryA) objsA = serviceA.selectByFields(Arrays.asList(keyword.split(",")));
      else objsB = serviceB.selectByFields(Arrays.asList(keyword.split(",")));
    }

    // Transport Time

    try {
      ReturnDataPack<UncertainBeanA> rdpA = new ReturnDataPack<>(needQueryA, affectedRows, objsA);
      ReturnDataPack<UncertainBeanB> rdpB = new ReturnDataPack<>(needQueryA, affectedRows, objsB);
      resp.getWriter().write((needQueryA ? rdpA : rdpB).toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 从参数图中获取值并构造数据容器。
   */
  protected abstract UncertainBeanA getAInstance();

  /**
   * 从参数图中获取值并构造数据容器。
   */
  protected abstract UncertainBeanB getBInstance();
}
