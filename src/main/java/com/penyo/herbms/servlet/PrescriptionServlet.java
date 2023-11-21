package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.PrescriptionBean;
import com.penyo.herbms.service.PrescriptionInfoService;
import com.penyo.herbms.service.PrescriptionService;
import com.penyo.herbms.util.NeedRebuild;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 经方和经方概要的请求处理层
 *
 * @author hawkkie
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
@NeedRebuild
@WebServlet(name = "PrescriptionServlet", urlPatterns = "/views/prescriptionServlet")
public class PrescriptionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrescriptionInfoService piService = new PrescriptionInfoService();
    PrescriptionService pService = new PrescriptionService();

    List<PrescriptionInfoBean> pis = new ArrayList<>();
    List<PrescriptionBean> ps = new ArrayList<>();

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

    boolean needQueryInfo = true;
    String oNeedQueryInfo = params.get("needQueryInfo");
    if (oNeedQueryInfo != null) needQueryInfo = oNeedQueryInfo.equals("1");

    // Update Part

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (opType == null) {
          if (needQueryInfo) {
            PrescriptionInfoBean pi = new PrescriptionInfoBean(id, params.get("name"), params.get("nickname"), params.get("description"));
            if (piService.selectById(id) == null) piService.add(pi);
            else piService.update(pi);
          } else {
            PrescriptionBean p = new PrescriptionBean(id, Integer.parseInt(params.get("prescriptionId")), Integer.parseInt(params.get("herbId")), params.get("dosage"), params.get("usage"));
            if (pService.selectById(id) == null) pService.add(p);
            else pService.update(p);
          }
        } else if (opType.equals("delete")) {
          if (needQueryInfo) piService.deleteById(id);
          else pService.deleteById(id);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Arbitrate Time

    if (isId) {
      if (needQueryInfo) pis.add(piService.selectById(Integer.parseInt(keyword)));
      else ps.add(pService.selectById(Integer.parseInt(keyword)));
    } else {
      if (needQueryInfo) pis = piService.selectByField(keyword);
      else ps = pService.selectByField(keyword);
    }

    // Transport Time

    req.getSession().setAttribute("needQueryInfo", needQueryInfo);
    req.getSession().setAttribute("list", needQueryInfo ? pis : ps);
    resp.sendRedirect("prescription.jsp");
  }
}