package net.penyo.herbms.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.penyo.herbms.pojo.Experience;
import net.penyo.herbms.pojo.ReturnDataPack;
import net.penyo.herbms.service.ExperienceService;
import net.penyo.herbms.service.HerbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 中草药使用心得的控制器代理
 *
 * @author Penyo
 * @see Experience
 * @see GenericController
 */
@Controller
public class ExperienceController extends GenericController<Experience> {
  @Override
  @RequestMapping("/use-experiences")
  @ResponseBody
  public String requestMain(HttpServletRequest request) {
    return requestMain(toMap(request), getService(ExperienceService.class)).toString();
  }

  @Override
  @RequestMapping("/use-experiences-specific")
  @ResponseBody
  public String requestSub(HttpServletRequest request) {
    List<String> hs = new ArrayList<>();

    ReturnDataPack<Experience> exps = requestMain(toMap(request), getService(ExperienceService.class));
    for (Experience o : exps.getObjs()) {
      StringBuilder hTemp = new StringBuilder("\"");
      hTemp.append(getService(HerbService.class).selectNameByExperienceId(o.getId()));
      hs.add(hTemp.append("\"").toString());
    }
    return new ReturnDataPack<>(hs).toString();
  }

  @Override
  public Experience getInstance(Map<String, String> params) {
    return new Experience(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("herbId")), params.get("derivation"), params.get("content"));
  }
}
