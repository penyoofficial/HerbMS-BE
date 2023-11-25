package com.penyo.herbms.service;

import com.penyo.herbms.dao.AbstractDAO;
import com.penyo.herbms.pojo.GenericBean;

import java.util.List;

/**
 * 抽象业务代理
 *
 * @author Penyo
 */
public interface AbstractService<UncertainBean extends GenericBean> extends AbstractDAO<UncertainBean> {
  @Override
  default List<UncertainBean> selectAll() {
    throw new UnsupportedOperationException();
  }
}
