package net.penyo.herbms.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.penyo.herbms.pojo.ItemDifferentiationInfo;
import net.penyo.herbms.pojo.ReturnDataPack;
import net.penyo.herbms.service.ItemDifferentiationInfoService;
import net.penyo.herbms.service.PrescriptionInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 条辩概要的控制器代理
 *
 * @author Penyo
 * @see ItemDifferentiationInfo
 * @see GenericController
 */
@Controller
public class ItemDifferentiationInfoController extends GenericController<ItemDifferentiationInfo> {
  @Override
  @RequestMapping("/use-item_differentiation_infos")
  @ResponseBody
  public String requestMain(HttpServletRequest request) {
    return requestMain(toMap(request), getService(ItemDifferentiationInfoService.class)).toString();
  }

  @Override
  @RequestMapping("/use-item_differentiation_infos-specific")
  @ResponseBody
  public String requestSub(HttpServletRequest request) {
    List<String> ps = new ArrayList<>();

    ReturnDataPack<ItemDifferentiationInfo> idtis = requestMain(toMap(request), getService(ItemDifferentiationInfoService.class));
    for (ItemDifferentiationInfo idti : idtis.getObjs()) {
      StringBuilder idtiTemp = new StringBuilder("\"");
      for (String name : getService(PrescriptionInfoService.class).selectNamesByIDTIId(idti.getId()))
        idtiTemp.append(name).append("/");
      if (idtiTemp.length() > 1)
        ps.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
    }
    return new ReturnDataPack<>(ps).toString();
  }

  @Override
  public ItemDifferentiationInfo getInstance(Map<String, String> params) {
    return new ItemDifferentiationInfo(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("content"), params.get("annotation"));
  }
}
