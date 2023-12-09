package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ItemDifferentiationService;

import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 条辩的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
@WebServlet({"/use-item_differentiations", "/use-item_differentiations-specific"})
public class ItemDifferentiationServlet extends GenericServlet<ItemDifferentiationBean, ItemDifferentiationService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    if (!req.getServletPath().contains("specific"))
      doProcess(req, resp, new ItemDifferentiationService(), true);
    else doSpecificProcess(req, resp, new ItemDifferentiationService());
  }

  @Override
  @SuppressWarnings("unchecked")
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ItemDifferentiationService idtService) {
    doResponseInJSON(resp, new ReturnDataPack<>(new ArrayList<>()));
  }

  @Override
  public ItemDifferentiationBean getInstance() {
    return new ItemDifferentiationBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("itemDifferentiationId")), Integer.parseInt(params.get("prescriptionId")), params.get("type"));
  }
}
