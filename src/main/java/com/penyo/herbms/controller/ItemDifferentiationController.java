package com.penyo.herbms.controller;

import com.penyo.herbms.pojo.ItemDifferentiation;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ItemDifferentiationService;

import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 条辩的控制器代理
 *
 * @author Penyo
 * @see ItemDifferentiation
 */
@Controller
public class ItemDifferentiationController extends GenericController<ItemDifferentiation> {
  @Override
  @RequestMapping("/use-item_differentiations")
  @ResponseBody
  public String requestMain(HttpServletRequest request) {
    return requestMain(toMap(request), getService(ItemDifferentiationService.class)).toString();
  }

  @Override
  @RequestMapping("/use-item_differentiations-specific")
  @ResponseBody
  public String requestSub(HttpServletRequest request) {
    return new ReturnDataPack<>(new ArrayList<>()).toString();
  }

  @Override
  public ItemDifferentiation getInstance(Map<String, String> params) {
    return new ItemDifferentiation(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("itemDifferentiationId")), Integer.parseInt(params.get("prescriptionId")), params.get("type"));
  }
}
