package com.penyo.herbms.service;

import com.penyo.herbms.pojo.PrescriptionInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 经方概要的业务层
 *
 * @author hawkkie
 */
public class PrescriptionInfoService extends AbstractService<PrescriptionInfoBean> {
    @Override
    public int add(PrescriptionInfoBean o) {
        return piDAO.add(o);
    }

    @Override
    public int deleteById(int id) {
        return piDAO.delete(id);
    }

    @Override
    public int update(PrescriptionInfoBean o) {
        return piDAO.update(o);
    }

    @Override
    public PrescriptionInfoBean selectById(int id) {
        return piDAO.select(id);
    }

    @Override
    public List<PrescriptionInfoBean> selectByFields(String... fields) {
        return piDAO.select(fields);
    }

    /**
     * 根据字段查找元素。
     */
    public List<PrescriptionInfoBean> selectByField(String field) {
        List<PrescriptionInfoBean> pis = new ArrayList<>();
        for (PrescriptionInfoBean h : piDAO.selectAll())
            if (h.getName().contains(field) || h.getNickname().contains(field) || h.getDescription().contains(field))
                pis.add(h);
        return pis;
    }
}
