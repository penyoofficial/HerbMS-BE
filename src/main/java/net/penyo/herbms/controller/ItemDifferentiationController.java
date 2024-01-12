package net.penyo.herbms.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.penyo.herbms.pojo.ItemDifferentiation;
import net.penyo.herbms.pojo.ReturnDataPack;
import net.penyo.herbms.service.ItemDifferentiationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * 条辩的控制器代理
 *
 * @author Penyo
 * @see ItemDifferentiation
 * @see GenericController
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
