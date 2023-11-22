package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.HerbBean;
import com.penyo.herbms.pojo.ExperienceBean;
import com.penyo.herbms.service.HerbService;
import com.penyo.herbms.service.ExperienceService;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 中药和中药使用心得的请求处理层
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.HerbBean
 * @see com.penyo.herbms.pojo.ExperienceBean
 */
@WebServlet(name = "HerbServlet", urlPatterns = "/herbServlet")
public class HerbServlet extends AbstractServlet<HerbBean, ExperienceBean, HerbService, ExperienceService> {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    doProcess(req, new HerbService(), new ExperienceService());
    resp.sendRedirect("herb");
  }

  @Override
  protected HerbBean getAInstance() {
    return new HerbBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("name"), params.get("nickname"), params.get("type"), params.get("description"), params.get("efficacy"), params.get("taste"), params.get("origin"), params.get("dosage"), params.get("taboo"), params.get("processing"));
  }

  @Override
  protected ExperienceBean getBInstance() {
    return new ExperienceBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("herbId")), params.get("derivation"), params.get("content"));
  }
}
