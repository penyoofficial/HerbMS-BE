package net.penyo.herbms.service;

import net.penyo.herbms.dao.AbstractDAO;
import net.penyo.herbms.pojo.GenericBean;

import java.util.List;

/**
 * 抽象业务代理
 *
 * @author Penyo
 * @see GenericBean
 * @see AbstractDAO
 */
public interface AbstractService<UnknownBean extends GenericBean> extends AbstractDAO<UnknownBean> {
  @Override
  default List<UnknownBean> selectAll() {
    throw new UnsupportedOperationException();
  }
}
