package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.ItemDifferentiationService;
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
 * 条辩和条辩概要的请求处理层
 *
 * @author lyh
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
@NeedRebuild
@WebServlet(name = "ItemDifferentiationServlet", urlPatterns = "/views/itemDifferentiationServlet")
public class ItemDifferentiationServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    ItemDifferentiationInfoService idtiService = new ItemDifferentiationInfoService();
    ItemDifferentiationService idtService = new ItemDifferentiationService();

    List<ItemDifferentiationInfoBean> idtis = new ArrayList<>();
    List<ItemDifferentiationBean> idts = new ArrayList<>();

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
            ItemDifferentiationInfoBean idti = new ItemDifferentiationInfoBean(id, Integer.parseInt(params.get("code")), params.get("content"), params.get("annotation"));
            if (idtiService.selectById(id) == null) idtiService.add(idti);
            else idtiService.update(idti);
          } else {
            ItemDifferentiationBean idt = new ItemDifferentiationBean(id, Integer.parseInt(params.get("itemDifferentionId")), Integer.parseInt(params.get("prescriptionId")), params.get("type"));
            if (idtService.selectById(id) == null) idtService.add(idt);
            else idtService.update(idt);
          }
        } else if (opType.equals("delete")) {
          if (needQueryA) idtiService.deleteById(id);
          else idtService.deleteById(id);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Arbitrate Time

    if (isId) {
      if (needQueryA) idtis.add(idtiService.selectById(Integer.parseInt(keyword)));
      else idts.add(idtService.selectById(Integer.parseInt(keyword)));
    } else {
      if (needQueryA) idtis = idtiService.selectByField(keyword);
      else idts = idtService.selectByField(keyword);
    }

    // Transport Time

    req.getSession().setAttribute("needQueryA", needQueryA);
    req.getSession().setAttribute("list", needQueryA ? idtis : idts);
    resp.sendRedirect("item-differentiation.jsp");
  }
}
