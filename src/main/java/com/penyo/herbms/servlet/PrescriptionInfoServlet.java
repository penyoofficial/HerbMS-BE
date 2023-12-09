package com.penyo.herbms.servlet;

import com.penyo.herbms.pojo.*;
import com.penyo.herbms.service.ItemDifferentiationInfoService;
import com.penyo.herbms.service.PrescriptionInfoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 处方概要的请求处理代理
 *
 * @author Penyo
 * @see com.penyo.herbms.pojo.PrescriptionInfoBean
 */
@WebServlet({"/use-prescription_infos", "/use-prescription_infos-specific"})
public class PrescriptionInfoServlet extends GenericServlet<PrescriptionInfoBean,PrescriptionInfoService> {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (!req.getServletPath().contains("specific")) doProcess(req, resp, new PrescriptionInfoService(), true);
        else doSpecificProcess(req, resp, new PrescriptionInfoService());
    }

    @Override
    public void doSpecificProcess(HttpServletRequest req, HttpServletResponse resp, PrescriptionInfoService serv) {
            List<String> idtis = new ArrayList<>();
            ReturnDataPack<PrescriptionInfoBean> pis = doProcess(req, resp,serv, false);
            for (PrescriptionInfoBean o : pis.getObjs()) {
                StringBuilder idtiTemp = new StringBuilder("\"");
                for (ItemDifferentiationInfoBean idti : new ItemDifferentiationInfoService().selectByPrescriptionId(o.getId()))
                    idtiTemp.append(idti.getContent()).append("/");
                if (idtiTemp.length() > 1)
                    idtis.add(idtiTemp.delete(idtiTemp.length() - 1, idtiTemp.length()).append("\"").toString());
            }
            doResponseInJSON(resp, new ReturnDataPack<>(idtis));

    }


        @Override
        public PrescriptionInfoBean getInstance () {
            return new PrescriptionInfoBean(Integer.parseInt(params.get("id")), params.get("name"), params.get("nickname"), params.get("description"));
        }
}
