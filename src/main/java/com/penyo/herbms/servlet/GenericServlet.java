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
public abstract class GenericServlet<UncertainBean extends GenericBean, UncertainService extends GenericService<UncertainBean>> extends HttpServlet implements AbstractServlet<UncertainBean, UncertainService> {
  /**
   * 请求参数图
   */
  protected final Map<String, String> params = new HashMap<>();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    doGet(req, resp);
  }

  @Override
  public synchronized ReturnDataPack<UncertainBean> doProcess(HttpServletRequest req, HttpServletResponse resp, UncertainService serv, boolean needBurn) {
    Enumeration<String> paramNames = req.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String key = paramNames.nextElement();
      params.put(key, req.getParameter(key));
    }

    String keyword = "";
    String keywordOri = params.get("keyword");
    if (keywordOri != null) keyword = keywordOri;

    boolean isId = false;
    String isIdOri = params.get("isId");
    if (isIdOri != null) isId = isIdOri.equals("on");

    List<UncertainBean> objs = new ArrayList<>();

    int affectedRows = -1;

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (opType.equals("delete")) {
          affectedRows = serv.delete(id);
        } else {
          UncertainBean obj = getInstance();
          if (opType.equals("add")) affectedRows = serv.add(obj);
          else affectedRows = serv.update(obj);
        }
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }

    if (isId) objs.add(serv.selectById(Integer.parseInt(keyword)));
    else objs = serv.selectByFields(Arrays.asList(keyword.split(",")));

    ReturnDataPack<UncertainBean> rdp = new ReturnDataPack<>(affectedRows, objs);
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
