package com.penyo.herbms.controller;

import com.penyo.herbms.pojo.Herb;
import com.penyo.herbms.pojo.ReturnDataPack;
import com.penyo.herbms.service.ExperienceService;
import com.penyo.herbms.service.HerbService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 中草药的控制器代理
 *
 * @author Penyo
 * @see Herb
 */
@Controller
public class HerbController extends GenericController<Herb> {
  @Override
  @RequestMapping("/use-herbs")
  @ResponseBody
  public String requestMain(HttpServletRequest request) {
    return requestMain(toMap(request), getService(HerbService.class)).toString();
  }

  @Override
  @RequestMapping("/use-herbs-specific")
  @ResponseBody
  public String requestSub(HttpServletRequest request) {
    List<String> exps = new ArrayList<>();

    ReturnDataPack<Herb> hs = requestMain(toMap(request), getService(HerbService.class));
    for (Herb o : hs.getObjs()) {
      StringBuilder expTemp = new StringBuilder("\"");
      for (String oo : getService(ExperienceService.class).selectContentsByHerbId(o.getId()))
        expTemp.append(oo);
      if (expTemp.length() > 1) exps.add(expTemp.append("\"").toString());
    }
    return new ReturnDataPack<>(exps).toString();
  }

  @Override
  public Herb getInstance(Map<String, String> params) {
    return new Herb(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("code")), params.get("name"), params.get("nickname"), params.get("type"), params.get("description"), params.get("efficacy"), params.get("taste"), params.get("origin"), params.get("dosage"), params.get("taboo"), params.get("processing"));
  }
}
