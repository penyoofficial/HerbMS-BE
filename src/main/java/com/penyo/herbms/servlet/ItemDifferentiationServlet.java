package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.ItemDifferentiationService;

import java.util.ArrayList;
import java.util.List;

import com.penyo.herbms.service.PrescriptionInfoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 条辩的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
@WebServlet({"/itemDifferentiationServlet", "/itemDifferentiationServletSpecific"})
public class ItemDifferentiationServlet extends GenericServlet<ItemDifferentiationInfoBean, ItemDifferentiationBean, ItemDifferentiationInfoService, ItemDifferentiationService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("Specific"))
      doProcess(req, resp, new ItemDifferentiationInfoService(), new ItemDifferentiationService(), true);
    else doSpecificProcess(req, resp, new ItemDifferentiationInfoService(), new ItemDifferentiationService());
  }

  @Override
  @SuppressWarnings("unchecked")
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ItemDifferentiationInfoService idtiService, ItemDifferentiationService idtService) {
    if (req.getParameter("needQueryA").equals("true")) {
      List<String> ps = new ArrayList<>();

      ReturnDataPack<ItemDifferentiationInfoBean> idtis = (ReturnDataPack<ItemDifferentiationInfoBean>) doProcess(req, resp, idtiService, idtService, false);
      for (ItemDifferentiationInfoBean idti : idtis.getList()) {
        StringBuilder idtiTemp = new StringBuilder("\"");
        for (PrescriptionInfoBean pi : new PrescriptionInfoService().selectByIDTIId(idti.getId()))
          idtiTemp.append(pi.getName()).append("/");
        if (idtiTemp.length() > 1) idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length());
        ps.add(idtiTemp.append("\"").toString());
      }
      doResponseInJSON(resp, new ReturnDataPack<>(ps));
    } else {
      doResponseInJSON(resp, new ReturnDataPack<>(new ArrayList<>()));
    }
  }

  @Override
  public ItemDifferentiationInfoBean getAInstance() {
    return new ItemDifferentiationInfoBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("content"), params.get("annotation"));
  }

  @Override
  public ItemDifferentiationBean getBInstance() {
    return new ItemDifferentiationBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("itemDifferentiationId")), Integer.parseInt(params.get("prescriptionId")), params.get("type"));
  }
}
