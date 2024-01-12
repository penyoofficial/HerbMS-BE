package net.penyo.herbms.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.penyo.herbms.pojo.PrescriptionInfo;
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
 * 处方概要的控制器代理
 *
 * @author Penyo
 * @see PrescriptionInfo
 * @see GenericController
 */
@Controller
public class PrescriptionInfoController extends GenericController<PrescriptionInfo> {
  @Override
  @RequestMapping("/use-prescription_infos")
  @ResponseBody
  public String requestMain(HttpServletRequest request) {
    return requestMain(toMap(request), getService(PrescriptionInfoService.class)).toString();
  }

  @Override
  @RequestMapping("/use-prescription_infos-specific")
  @ResponseBody
  public String requestSub(HttpServletRequest request) {
    List<String> idtis = new ArrayList<>();

    ReturnDataPack<PrescriptionInfo> pis = requestMain(toMap(request), getService(PrescriptionInfoService.class));
    for (PrescriptionInfo o : pis.getObjs()) {
      StringBuilder idtiTemp = new StringBuilder("\"");
      for (String content : getService(ItemDifferentiationInfoService.class).selectContentsByPrescriptionId(o.getId()))
        idtiTemp.append(content).append("/");
      if (idtiTemp.length() > 1)
        idtis.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
    }
    return new ReturnDataPack<>(idtis).toString();
  }

  @Override
  public PrescriptionInfo getInstance(Map<String, String> params) {
    return new PrescriptionInfo(Integer.parseInt(params.get("id")), params.get("name"), params.get("nickname"), params.get("description"));
  }
}
