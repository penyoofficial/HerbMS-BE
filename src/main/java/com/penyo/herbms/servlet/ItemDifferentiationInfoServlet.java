package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.PrescriptionInfoBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.PrescriptionInfoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 条辩概要的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 */
@WebServlet({"/use-item_differentiation_infos", "/use-item_differentiation_infos-specific"})
public class ItemDifferentiationInfoServlet extends GenericServlet<ItemDifferentiationInfoBean, ItemDifferentiationInfoService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific"))
      doProcess(req, resp, new ItemDifferentiationInfoService(), true);
    else doSpecificProcess(req, resp, new ItemDifferentiationInfoService());
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ItemDifferentiationInfoService serv) {
    List<String> ps = new ArrayList<>();

    ReturnDataPack<ItemDifferentiationInfoBean> idtis = doProcess(req, resp, serv, false);
    for (ItemDifferentiationInfoBean idti : idtis.getObjs()) {
      StringBuilder idtiTemp = new StringBuilder("\"");
      for (PrescriptionInfoBean pi : new PrescriptionInfoService().selectByIDTIId(idti.getId()))
        idtiTemp.append(pi.getName()).append("/");
      if (idtiTemp.length() > 1)
          ps.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(ps));
  }

  @Override
  public ItemDifferentiationInfoBean getInstance() {
    return new ItemDifferentiationInfoBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("content"), params.get("annotation"));
  }
}

