package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.*;
import com.penyo.herbms.service.HerbService;
import com.penyo.herbms.service.PrescriptionService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 处方的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionBean
 */
@WebServlet({"/use-prescriptions", "/use-prescriptions-specific"})
public class PrescriptionServlet extends GenericServlet<PrescriptionBean, PrescriptionService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific"))
      doProcess(req, resp, new PrescriptionService(), true);
    else doSpecificProcess(req, resp, new PrescriptionService());
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp,PrescriptionService serv){
    List<String> annotations = new ArrayList<>();
    ReturnDataPack<PrescriptionBean> ps = doProcess(req,resp,serv,false);
    for (PrescriptionBean o : ps.getObjs()) {
      StringBuilder annoTemp = new StringBuilder("\"");
      for (String oo : new HerbService().selectDescriptionsByPrescriptionId(o.getId()))
        annoTemp.append(new HerbService().selectNamesByPrescriptionId(o.getId())).append("/");
        annoTemp.append(new HerbService().selectDescriptionsByPrescriptionId(o.getId())).append("/");
      if (annoTemp.length() > 1) annoTemp.delete(annoTemp.length() - 1, annoTemp.length()).append("：");
      PrescriptionInfoBean oo = serv.selectPrescriptionId(o.getId());
      annoTemp.append(oo.getName());
      if (!oo.getNickname().equals("无")) annoTemp.append("（").append(oo.getNickname()).append("）");
      annoTemp.append("：").append(oo.getDescription());
      annotations.add((annoTemp.append("\"").toString()));
    }
    doResponseInJSON(resp, new ReturnDataPack<>(annotations));
  }

  @Override
  public PrescriptionBean getInstance() {
    return new PrescriptionBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("prescriptionId")), Integer.parseInt("herbId"), params.get("dosage"), params.get("usage"));
  }
}
