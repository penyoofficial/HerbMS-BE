package com.penyo.herbms.dao;

import com.penyo.herbms.pojo.GenericBean;

import java.util.List;

/**
 * 抽象数据访问代理
 *
 * @author Penyo
 */
public interface AbstractDAO<UnknownBean extends GenericBean> {
  /**
   * 添加单个元素。
   */
  int add(UnknownBean o);

  /**
   * 删除单个元素。
   */
  int delete(int id);

  /**
   * 修改单个元素。
   */
  int update(UnknownBean o);

  /**
   * 根据 ID 查找单个元素。
   */
  UnknownBean selectById(int id);

  /**
   * 根据关键字集合模糊查找多个元素。
   */
  List<UnknownBean> selectByFields(List<String> fields);

  /**
   * 查找全部元素。
   */
  List<UnknownBean> selectAll();
}
