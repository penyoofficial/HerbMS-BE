package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationInfo;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.PrescriptionInfoService;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

/**
 * 条辩概要的请求处理代理
 *
 * @author Penyo
 * @see ItemDifferentiationInfo
 */
@Controller
@WebServlet({"/use-item_differentiation_infos", "/use-item_differentiation_infos-specific"})
public class ItemDifferentiationInfoServlet extends GenericServlet<ItemDifferentiationInfo, ItemDifferentiationInfoService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific")) doProcess(req, resp, getService(ItemDifferentiationInfoService.class), true);
    else doSpecificProcess(req, resp, getService(ItemDifferentiationInfoService.class));
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ItemDifferentiationInfoService serv) {
    List<String> ps = new ArrayList<>();

    ReturnDataPack<ItemDifferentiationInfo> idtis = doProcess(req, resp, serv, false);
    for (ItemDifferentiationInfo idti : idtis.getObjs()) {
      StringBuilder idtiTemp = new StringBuilder("\"");
      for (String name : getService(PrescriptionInfoService.class).selectNamesByIDTIId(idti.getId()))
        idtiTemp.append(name).append("/");
      if (idtiTemp.length() > 1)
        ps.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
    }
    doResponseInJSON(resp, new ReturnDataPack<>(ps));
  }

  @Override
  public ItemDifferentiationInfo getInstance() {
    return new ItemDifferentiationInfo(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("content"), params.get("annotation"));
  }
}
