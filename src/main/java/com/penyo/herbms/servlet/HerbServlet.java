package com.penyo.herbms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.penyo.herbms.dao.ExperienceDAO;
import com.penyo.herbms.dao.HerbDAO;
import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.pojo.HerbBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 药品和心得的业务层。
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
@WebServlet(name="HerbServlet", urlPatterns = "/views/herbServlet")
public class HerbServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HerbDAO hDAO = new HerbDAO();
    ExperienceDAO expDAO = new ExperienceDAO();

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
    if (keyword == null)
      keyword = "";

    boolean isId = false;
    String oIsId = params.get("isId");
    if (oIsId != null)
      isId = oIsId.equals("on");

    boolean needQueryHerb = true;
    String oNeedQueryHerb = params.get("needQueryHerb");
    if (oNeedQueryHerb != null)
      needQueryHerb = oNeedQueryHerb.equals("1");

    // Update Part

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (opType == null) {
          if (needQueryHerb) {
            HerbBean h = new HerbBean(
                id,
                Integer.parseInt(params.get("code")),
                params.get("name"),
                params.get("nickname"),
                params.get("type"),
                params.get("description"),
                params.get("efficacy"),
                params.get("taste"),
                params.get("origin"),
                params.get("dosage"),
                params.get("taboo"),
                params.get("processing"));
            if (hDAO.select(id) == null)
              hDAO.add(h);
            else
              hDAO.update(h);
          } else {
            ExperienceBean exp = new ExperienceBean(
                id,
                Integer.parseInt(params.get("herbId")),
                params.get("derivation"),
                params.get("content"));
            if (expDAO.select(id) == null)
              expDAO.add(exp);
            else
              expDAO.update(exp);
          }
        } else if (opType.equals("delete")) {
          if (needQueryHerb)
            hDAO.delete(id);
          else
            expDAO.delete(id);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Arbitrate Time

    if (isId) {
      if (needQueryHerb)
        hs.add(hDAO.select(Integer.parseInt(keyword)));
      else
        exps.add(expDAO.select(Integer.parseInt(keyword)));
    } else {
      if (needQueryHerb)
        hs = hDAO.selectByField(keyword);
      else
        exps = expDAO.selectByField(keyword);
    }

    // Transport Time

    req.getSession().setAttribute("needQueryHerb", needQueryHerb);
    req.getSession().setAttribute("list", needQueryHerb ? hs : exps);
    resp.sendRedirect("herb.jsp");
  }
}
