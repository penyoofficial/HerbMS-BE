package com.penyo.herbms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.pojo.HerbBean;

import com.penyo.herbms.service.ExperienceService;
import com.penyo.herbms.service.HerbService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 中药和中药使用心得的请求处理层
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
@WebServlet(name = "HerbServlet", urlPatterns = "/views/herbServlet")
public class HerbServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HerbService hService = new HerbService();
    ExperienceService expService = new ExperienceService();

    List<HerbBean> hs = new ArrayList<>();
    List<ExperienceBean> exps = new ArrayList<>();

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

    boolean needQueryHerb = true;
    String oNeedQueryHerb = params.get("needQueryHerb");
    if (oNeedQueryHerb != null) needQueryHerb = oNeedQueryHerb.equals("1");

    // Update Part

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (opType == null) {
          if (needQueryHerb) {
            HerbBean h = new HerbBean(id, Integer.parseInt(params.get("code")), params.get("name"), params.get("nickname"), params.get("type"), params.get("description"), params.get("efficacy"), params.get("taste"), params.get("origin"), params.get("dosage"), params.get("taboo"), params.get("processing"));
            if (hService.selectById(id) == null) hService.add(h);
            else hService.update(h);
          } else {
            ExperienceBean exp = new ExperienceBean(id, Integer.parseInt(params.get("herbId")), params.get("derivation"), params.get("content"));
            if (expService.selectById(id) == null) expService.add(exp);
            else expService.update(exp);
          }
        } else if (opType.equals("delete")) {
          if (needQueryHerb) hService.deleteById(id);
          else expService.deleteById(id);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Arbitrate Time

    if (isId) {
      if (needQueryHerb) hs.add(hService.selectById(Integer.parseInt(keyword)));
      else exps.add(expService.selectById(Integer.parseInt(keyword)));
    } else {
      if (needQueryHerb) hs = hService.selectByField(keyword);
      else exps = expService.selectByField(keyword);
    }

    // Transport Time

    req.getSession().setAttribute("needQueryHerb", needQueryHerb);
    req.getSession().setAttribute("list", needQueryHerb ? hs : exps);
    resp.sendRedirect("herb.jsp");
  }
}
