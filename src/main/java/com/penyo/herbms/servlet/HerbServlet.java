package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.HerbService;
import com.penyo.herbms.service.ExperienceService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 中药和中药使用心得的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 */
@WebServlet({"/herbServlet", "/herbServletSpecific"})
public class HerbServlet extends GenericServlet<HerbBean, HerbService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("Specific")) doProcess(req, resp, new HerbService(), true);
    else doSpecificProcess(req, resp, new HerbService());
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, HerbService serv) {
    List<String> exps = new ArrayList<>();

    ReturnDataPack<HerbBean> hs = doProcess(req, resp, serv, false);
    for (HerbBean o : hs.getObjs()) {
      StringBuilder expTemp = new StringBuilder("\"");
      for (String oo : new ExperienceService().selectContentsByHerbId(o.getId()))
        expTemp.append(oo);
      if (expTemp.length() > 1) exps.add(expTemp.append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(exps));
  }

  @Override
  public HerbBean getInstance() {
    return new HerbBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("name"), params.get("nickname"), params.get("type"), params.get("description"), params.get("efficacy"), params.get("taste"), params.get("origin"), params.get("dosage"), params.get("taboo"), params.get("processing"));
  }
}
