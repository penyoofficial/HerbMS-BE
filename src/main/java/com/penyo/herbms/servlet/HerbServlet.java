package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.Herb;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ExperienceService;
import com.penyo.herbms.service.HerbService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

/**
 * 中草药的请求处理代理
 *
 * @author Penyo
 * @see Herb
 */
@Controller
@WebServlet({"/use-herbs", "/use-herbs-specific"})
public class HerbServlet extends GenericServlet<Herb, HerbService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific")) doProcess(req, resp, getService(HerbService.class), true);
    else doSpecificProcess(req, resp, getService(HerbService.class));
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, HerbService serv) {
    List<String> exps = new ArrayList<>();

    ReturnDataPack<Herb> hs = doProcess(req, resp, serv, false);
    for (Herb o : hs.getObjs()) {
      StringBuilder expTemp = new StringBuilder("\"");
      for (String oo : getService(ExperienceService.class).selectContentsByHerbId(o.getId()))
        expTemp.append(oo);
      if (expTemp.length() > 1) exps.add(expTemp.append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(exps));
  }

  @Override
  public Herb getInstance() {
    return new Herb(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("name"), params.get("nickname"), params.get("type"), params.get("description"), params.get("efficacy"), params.get("taste"), params.get("origin"), params.get("dosage"), params.get("taboo"), params.get("processing"));
  }
}
