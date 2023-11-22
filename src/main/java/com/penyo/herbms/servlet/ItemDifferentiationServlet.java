package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.ItemDifferentiationInfoBean;
import com.penyo.herbms.pojo.ItemDifferentiationBean;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.ItemDifferentiationService;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 条辩和条辩概要的请求处理层
 *
 * @author lyh
 * @see com.penyo.herbms.pojo.ItemDifferentiationInfoBean
 * @see com.penyo.herbms.pojo.ItemDifferentiationBean
 */
@WebServlet(name = "ItemDifferentiationServlet", urlPatterns = "/itemDifferentiationServlet")
public class ItemDifferentiationServlet extends AbstractServlet<ItemDifferentiationInfoBean, ItemDifferentiationBean, ItemDifferentiationInfoService, ItemDifferentiationService> {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    doProcess(req, new ItemDifferentiationInfoService(), new ItemDifferentiationService());
    resp.sendRedirect("item-differentiation");
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
