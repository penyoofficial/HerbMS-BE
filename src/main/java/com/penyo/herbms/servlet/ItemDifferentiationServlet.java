package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.ItemDifferentiationService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 条辩和条辩概要的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
@WebServlet("/itemDifferentiationServlet")
public class ItemDifferentiationServlet extends GenericServlet<ItemDifferentiationInfoBean, ItemDifferentiationBean, ItemDifferentiationInfoService, ItemDifferentiationService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    doProcess(req, resp, new ItemDifferentiationInfoService(), new ItemDifferentiationService());
    params.clear();
  }

  @Override
  protected ItemDifferentiationInfoBean getAInstance() {
    return new ItemDifferentiationInfoBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("content"), params.get("annotation"));
  }

  @Override
  protected ItemDifferentiationBean getBInstance() {
    return new ItemDifferentiationBean(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("itemDifferentiationId")), Integer.parseInt(params.get("prescriptionId")), params.get("type"));
  }
}
