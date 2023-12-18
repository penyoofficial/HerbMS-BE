package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.Prescription;
import com.penyo.herbms.pojo.PrescriptionInfo;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.HerbService;
import com.penyo.herbms.service.PrescriptionService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

/**
 * 处方的请求处理代理
 *
 * @author Penyo
 * @see Prescription
 */
@Controller
@WebServlet({"/use-prescriptions", "/use-prescriptions-specific"})
public class PrescriptionServlet extends GenericServlet<Prescription, PrescriptionService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific")) doProcess(req, resp, getService(PrescriptionService.class), true);
    else doSpecificProcess(req, resp, getService(PrescriptionService.class));
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, PrescriptionService serv) {
    List<String> annotations = new ArrayList<>();

    ReturnDataPack<Prescription> ps = doProcess(req, resp, serv, false);
    for (Prescription o : ps.getObjs()) {
      StringBuilder annoTemp = new StringBuilder("\"");
      annoTemp.append(getService(HerbService.class).selectNamesAndDescriptionsByPrescriptionId(o.getId())).append("/");
      if (annoTemp.length() > 1) annoTemp.delete(annoTemp.length() - 1, annoTemp.length()).append("：");
      PrescriptionInfo oo = serv.selectPrescriptionInfoByPrescriptionId(o.getId());
      annoTemp.append(oo.getName());
      if (!oo.getNickname().equals("无")) annoTemp.append("（").append(oo.getNickname()).append("）");
      annoTemp.append("：").append(oo.getDescription());
      annotations.add((annoTemp.append("\"").toString()));
    }
    doResponseInJSON(resp, new ReturnDataPack<>(annotations));
  }

  @Override
  public Prescription getInstance() {
    return new Prescription(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("prescriptionId")), Integer.parseInt("herbId"), params.get("dosage"), params.get("usage"));
  }
}
