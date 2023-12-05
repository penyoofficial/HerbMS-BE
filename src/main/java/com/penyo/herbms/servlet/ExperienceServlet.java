package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ExperienceService;
import com.penyo.herbms.service.HerbService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 中草药使用心得的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
@WebServlet({"/experiencesServlet", "/experiencesServletSpecific"})
public class ExperienceServlet extends GenericServlet<ExperienceBean, ExperienceService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("Specific")) doProcess(req, resp, new ExperienceService(), true);
    else doSpecificProcess(req, resp, new ExperienceService());
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ExperienceService serv) {
    List<String> hs = new ArrayList<>();

    ReturnDataPack<ExperienceBean> exps = doProcess(req, resp, serv, false);
    for (ExperienceBean o : exps.getObjs()) {
      StringBuilder hTemp = new StringBuilder("\"");
      hTemp.append(new HerbService().selectNameByExperienceId(o.getId()));
      hs.add(hTemp.append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(hs));
  }

  @Override
  public ExperienceBean getInstance() {
    return new ExperienceBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("herbId")), params.get("derivation"), params.get("content"));
  }
}
