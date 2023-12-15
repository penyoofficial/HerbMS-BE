package com.penyo.herbms.servlet;

import com.penyo.herbms.HerbMSContext;
import com.penyo.herbms.pojo.ItemDifferentiation;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ItemDifferentiationService;

import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

/**
 * 条辩的请求处理代理
 *
 * @author Penyo
 * @see ItemDifferentiation
 */
@Controller
@WebServlet({"/use-item_differentiations", "/use-item_differentiations-specific"})
public class ItemDifferentiationServlet extends GenericServlet<ItemDifferentiation, ItemDifferentiationService> {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    ItemDifferentiationService serv = HerbMSContext.getService(ItemDifferentiationService.class);

    if (!req.getServletPath().contains("specific")) doProcess(req, resp, serv, true);
    else doSpecificProcess(req, resp, serv);
  }

  @Override
  public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, ItemDifferentiationService idtService) {
    doResponseInJSON(resp, new ReturnDataPack<>(new ArrayList<>()));
  }

  @Override
  public ItemDifferentiation getInstance() {
    return new ItemDifferentiation(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("itemDifferentiationId")), Integer.parseInt(params.get("prescriptionId")), params.get("type"));
  }
}
