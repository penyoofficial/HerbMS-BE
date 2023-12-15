package com.penyo.herbms.servlet;

import com.penyo.herbms.HerbMSContext;
import com.penyo.herbms.pojo.*;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.PrescriptionInfoService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

/**
 * 处方概要的请求处理代理
 *
 * @author Penyo
 * @see PrescriptionInfo
 */
@Controller
@WebServlet({"/use-prescription_infos", "/use-prescription_infos-specific"})
public class PrescriptionInfoServlet extends GenericServlet<PrescriptionInfo, PrescriptionInfoService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    PrescriptionInfoService serv = HerbMSContext.getService(PrescriptionInfoService.class);

    if (!req.getServletPath().contains("specific")) doProcess(req, resp, serv, true);
    else doSpecificProcess(req, resp, serv);
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, PrescriptionInfoService serv) {
    List<String> idtis = new ArrayList<>();

    ReturnDataPack<PrescriptionInfo> pis = doProcess(req, resp, serv, false);
    for (PrescriptionInfo o : pis.getObjs()) {
      StringBuilder idtiTemp = new StringBuilder("\"");
      for (String content : HerbMSContext.getService(ItemDifferentiationInfoService.class).selectContentsByPrescriptionId(o.getId()))
        idtiTemp.append(content).append("/");
      if (idtiTemp.length() > 1)
        idtis.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(idtis));
  }

  @Override
  public PrescriptionInfo getInstance() {
    return new PrescriptionInfo(Integer.parseInt(params.get("id")), params.get("name"), params.get("nickname"), params.get("description"));
  }
}
