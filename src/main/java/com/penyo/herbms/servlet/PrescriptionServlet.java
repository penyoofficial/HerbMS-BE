package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.PrescriptionBean;
import com.penyo.herbms.service.PrescriptionInfoService;
import com.penyo.herbms.service.PrescriptionService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 经方和经方概要的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
@WebServlet("/prescriptionServlet")
public class PrescriptionServlet extends GenericServlet<PrescriptionInfoBean, PrescriptionBean, PrescriptionInfoService, PrescriptionService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    doProcess(req, resp, new PrescriptionInfoService(), new PrescriptionService());
    params.clear();
  }

  @Override
  protected PrescriptionInfoBean getAInstance() {
    return new PrescriptionInfoBean(Integer.parseInt(params.get("id")), params.get("name"), params.get("nickname"), params.get("description"));
  }

  @Override
  protected PrescriptionBean getBInstance() {
    return new PrescriptionBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("prescriptionId")), Integer.parseInt("herbId"), params.get("dosage"), params.get("usage"));
  }
}
