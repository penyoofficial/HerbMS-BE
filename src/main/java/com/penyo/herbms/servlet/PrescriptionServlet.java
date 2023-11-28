package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.*;
import com.penyo.herbms.service.HerbService;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.PrescriptionInfoService;
import com.penyo.herbms.service.PrescriptionService;

import java.util.ArrayList;
import java.util.List;

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
@WebServlet({"/prescriptionServlet", "/prescriptionServletSpecific"})
public class PrescriptionServlet extends GenericServlet<PrescriptionInfoBean, PrescriptionBean, PrescriptionInfoService, PrescriptionService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("Specific"))
      doProcess(req, resp, new PrescriptionInfoService(), new PrescriptionService(), true);
    else doSpecificProcess(req, resp, new PrescriptionInfoService(), new PrescriptionService());
  }

  @Override
  @SuppressWarnings("unchecked")
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, PrescriptionInfoService piService, PrescriptionService pService) {
    if (req.getParameter("needQueryA").equals("true")) {
      List<String> idtis = new ArrayList<>();

      ReturnDataPack<PrescriptionInfoBean> pis = (ReturnDataPack<PrescriptionInfoBean>) doProcess(req, resp, piService, pService, false);
      for (PrescriptionInfoBean o : pis.getList()) {
        StringBuilder idtiTemp = new StringBuilder("\"");
        for (ItemDifferentiationInfoBean idti : new ItemDifferentiationInfoService().selectByPrescriptionId(o.getId()))
          idtiTemp.append(idti.getContent()).append("/");
        if (idtiTemp.length() > 1)
          idtis.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
      }
      doResponseInJSON(resp, new ReturnDataPack<>(idtis));
    } else {
      List<String> annotations = new ArrayList<>();

      ReturnDataPack<PrescriptionBean> ps = (ReturnDataPack<PrescriptionBean>) doProcess(req, resp, piService, pService, false);
      for (PrescriptionBean o : ps.getList()) {
        StringBuilder annoTemp = new StringBuilder("\"");
        for (HerbBean oo : new HerbService().selectByPrescriptionId(o.getId()))
          annoTemp.append(oo.getName()).append("/");
        if (annoTemp.length() > 1) annoTemp.delete(annoTemp.length() - 1, annoTemp.length()).append("：");
        PrescriptionInfoBean oo = piService.selectByPrescriptionId(o.getId());
        annoTemp.append(oo.getName());
        if (!oo.getNickname().equals("无")) annoTemp.append("（").append(oo.getNickname()).append("）");
        annoTemp.append("：").append(oo.getDescription());
        annotations.add((annoTemp.append("\"").toString()));
      }
      doResponseInJSON(resp, new ReturnDataPack<>(annotations));
    }
  }

  @Override
  public PrescriptionInfoBean getAInstance() {
    return new PrescriptionInfoBean(Integer.parseInt(params.get("id")), params.get("name"), params.get("nickname"), params.get("description"));
  }

  @Override
  public PrescriptionBean getBInstance() {
    return new PrescriptionBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("prescriptionId")), Integer.parseInt("herbId"), params.get("dosage"), params.get("usage"));
  }
}
