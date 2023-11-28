package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ExperienceBean;
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
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
@WebServlet({"/herbServlet", "/herbServletSpecific"})
public class HerbServlet extends GenericServlet<HerbBean, ExperienceBean, HerbService, ExperienceService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("Specific"))
      doProcess(req, resp, new HerbService(), new ExperienceService(), true);
    else doSpecificProcess(req, resp, new HerbService(), new ExperienceService());
  }

  @Override
  @SuppressWarnings("unchecked")
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, HerbService hService, ExperienceService expService) {
    if (req.getParameter("needQueryA").equals("true")) {
      List<String> exps = new ArrayList<>();

      ReturnDataPack<HerbBean> hs = (ReturnDataPack<HerbBean>) doProcess(req, resp, hService, expService, false);
      for (HerbBean o : hs.getList()) {
        StringBuilder expTemp = new StringBuilder("\"");
        for (ExperienceBean oo : expService.selectByHerbId(o.getId()))
          expTemp.append(oo.getContent());
        if (expTemp.length() > 1) exps.add(expTemp.append("\"").toString());
      }
      doResponseInJSON(resp, new ReturnDataPack<>(exps));
    } else {
      List<String> hs = new ArrayList<>();

      ReturnDataPack<ExperienceBean> exps = (ReturnDataPack<ExperienceBean>) doProcess(req, resp, hService, expService, false);
      for (ExperienceBean o : exps.getList()) {
        StringBuilder hTemp = new StringBuilder("\"");
        hTemp.append(hService.selectByExperienceId(o.getId()).getName());
        hs.add(hTemp.append("\"").toString());
      }
      doResponseInJSON(resp, new ReturnDataPack<>(hs));
    }
  }

  @Override
  public HerbBean getAInstance() {
    return new HerbBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("name"), params.get("nickname"), params.get("type"), params.get("description"), params.get("efficacy"), params.get("taste"), params.get("origin"), params.get("dosage"), params.get("taboo"), params.get("processing"));
  }

  @Override
  public ExperienceBean getBInstance() {
    return new ExperienceBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("herbId")), params.get("derivation"), params.get("content"));
  }
}
