package net.penyo.herbms.controller;

import net.penyo.herbms.pojo.GenericBean;
import net.penyo.herbms.pojo.ReturnDataPack;
import net.penyo.herbms.service.GenericService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 通用控制器代理
 *
 * @author Penyo
 * @see GenericBean
 * @see AbstractController
 */
@CrossOrigin("*")
public abstract class GenericController<UnknownBean extends GenericBean> implements AbstractController<UnknownBean> {
  /**
   * 通用处理主要请求。
   */
  public ReturnDataPack<UnknownBean> requestMain(Map<String, String> params, GenericService<UnknownBean> serv) {
    String keyword = "";
    String keywordOri = params.get("keyword");
    if (keywordOri != null) keyword = keywordOri;

    boolean isId = false;
    String isIdOri = params.get("isId");
    if (isIdOri != null) isId = isIdOri.equals("on");

    List<UnknownBean> objs = new ArrayList<>();

    int affectedRows = -1;

    String oId = params.get("id");
    if (oId != null) {
      String opType = params.get("opType");
      try {
        int id = Integer.parseInt(oId);
        if (opType.equals("delete")) {
          affectedRows = serv.delete(id);
        } else {
          UnknownBean obj = getInstance(params);
          if (opType.equals("add")) affectedRows = serv.add(obj);
          else affectedRows = serv.update(obj);
        }
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }

    if (isId) objs.add(serv.selectById(Integer.parseInt(keyword)));
    else objs = serv.selectByFields(Arrays.asList(keyword.split(",")));

    return new ReturnDataPack<>(affectedRows, objs);
  }
}
