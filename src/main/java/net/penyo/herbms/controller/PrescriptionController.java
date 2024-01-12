package net.penyo.herbms.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.penyo.herbms.pojo.Prescription;
import net.penyo.herbms.pojo.PrescriptionInfo;
import net.penyo.herbms.pojo.ReturnDataPack;
import net.penyo.herbms.service.HerbService;
import net.penyo.herbms.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处方的控制器代理
 *
 * @author Penyo
 * @see Prescription
 * @see GenericController
 */
@Controller
public class PrescriptionController extends GenericController<Prescription> {
  @Override
  @RequestMapping("/use-prescriptions")
  @ResponseBody
  public String requestMain(HttpServletRequest request) {
    return requestMain(toMap(request), getService(PrescriptionService.class)).toString();
  }

  @Override
  @RequestMapping("/use-prescriptions-specific")
  @ResponseBody
  public String requestSub(HttpServletRequest request) {
    List<String> annotations = new ArrayList<>();

    ReturnDataPack<Prescription> ps = requestMain(toMap(request), getService(PrescriptionService.class));
    for (Prescription o : ps.getObjs()) {
      StringBuilder annoTemp = new StringBuilder("\"");
      annoTemp.append(getService(HerbService.class).selectNamesAndDescriptionsByPrescriptionId(o.getId())).append("/");
      if (annoTemp.length() > 1) annoTemp.delete(annoTemp.length() - 1, annoTemp.length()).append("：");
      PrescriptionInfo oo = getService(PrescriptionService.class).selectPrescriptionInfoByPrescriptionId(o.getId());
      annoTemp.append(oo.getName());
      if (!oo.getNickname().equals("无")) annoTemp.append("（").append(oo.getNickname()).append("）");
      annoTemp.append("：").append(oo.getDescription());
      annotations.add((annoTemp.append("\"").toString()));
    }
    return new ReturnDataPack<>(annotations).toString();
  }

  @Override
  public Prescription getInstance(Map<String, String> params) {
    return new Prescription(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("prescriptionId")), Integer.parseInt("herbId"), params.get("dosage"), params.get("usage"));
  }
}
