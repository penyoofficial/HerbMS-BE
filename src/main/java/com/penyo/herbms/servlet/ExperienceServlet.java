package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.Experience;
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
 * 中草药使用心得的请求处理代理
 *
 * @author Penyo
 * @see Experience
 */
@Controller
@WebServlet({"/use-experiences", "/use-experiences-specific"})
public class ExperienceServlet extends GenericServlet<Experience, ExperienceService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific")) doProcess(req, resp, getService(ExperienceService.class), true);
    else doSpecificProcess(req, resp, getService(ExperienceService.class));
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ExperienceService serv) {
    List<String> hs = new ArrayList<>();

    ReturnDataPack<Experience> exps = doProcess(req, resp, serv, false);
    for (Experience o : exps.getObjs()) {
      StringBuilder hTemp = new StringBuilder("\"");
      hTemp.append(getService(HerbService.class).selectNameByExperienceId(o.getId()));
      hs.add(hTemp.append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(hs));
  }

  @Override
  public Experience getInstance() {
    return new Experience(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("herbId")), params.get("derivation"), params.get("content"));
  }
}
