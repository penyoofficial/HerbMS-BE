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
public abstract class GenericServlet<UncertainBeanA extends GenericBean, UncertainBeanB extends GenericBean, UncertainServiceA extends GenericService<UncertainBeanA>, UncertainServiceB extends GenericService<UncertainBeanB>> extends HttpServlet implements AbstractServlet<UncertainBeanA, UncertainBeanB, UncertainServiceA, UncertainServiceB> {
  /**
   * 请求参数图
   */
  protected final Map<String, String> params = new HashMap<>();

  /**
   * 搜索内容
   */
  protected String keyword = "";
  /**
   * 是否唯一确定元素
   */
  protected boolean isId = false;
  /**
   * 是否查询表 A
   */
  protected boolean needQueryA = true;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    doGet(req, resp);
  }

  @Override
  public void doInitParams(HttpServletRequest req) {
    Enumeration<String> paramNames = req.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String key = paramNames.nextElement();
      params.put(key, req.getParameter(key));
    }

    String keyword = params.get("keyword");
    if (keyword != null) this.keyword = keyword;

    String isId = params.get("isId");
    if (isId != null) this.isId = isId.equals("on");

    String needQueryA = params.get("needQueryA");
    if (needQueryA != null) this.needQueryA = needQueryA.equals("true");
  }

  @Override
  public void doEraseParams() {
    params.clear();
    keyword = "";
    isId = false;
    needQueryA = true;
  }

  @Override
  public ReturnDataPack<? extends GenericBean> doProcess(HttpServletResponse resp, UncertainServiceA serviceA, UncertainServiceB serviceB, boolean needBurn) {
    List<UncertainBeanA> objsA = new ArrayList<>();
    List<UncertainBeanB> objsB = new ArrayList<>();

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

    if (isId) {
      if (needQueryA) objsA.add(serviceA.selectById(Integer.parseInt(keyword)));
      else objsB.add(serviceB.selectById(Integer.parseInt(keyword)));
    } else {
      if (needQueryA) objsA = serviceA.selectByFields(Arrays.asList(keyword.split(",")));
      else objsB = serviceB.selectByFields(Arrays.asList(keyword.split(",")));
    }

    ReturnDataPack<? extends GenericBean> rdp;
    if (needQueryA) rdp = new ReturnDataPack<>(true, affectedRows, objsA);
    else rdp = new ReturnDataPack<>(false, affectedRows, objsB);
    if (needBurn) doResponseInJSON(resp, rdp);
    return rdp;
  }

  @Override
  public void doResponseInJSON(HttpServletResponse resp, ReturnDataPack<?> rdp) {
    if (rdp == null) return;
    try {
      resp.getWriter().write(rdp.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
