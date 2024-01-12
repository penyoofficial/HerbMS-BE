package net.penyo.herbms.service;

import net.penyo.herbms.pojo.ItemDifferentiationInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 条辩概要的业务代理
 *
 * @author Penyo
 * @see ItemDifferentiationInfo
 * @see GenericService
 */
@Service
public class ItemDifferentiationInfoService extends GenericService<ItemDifferentiationInfo> {
  @Override
  public int add(ItemDifferentiationInfo o) {
    return itemDifferentiationInfoDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return itemDifferentiationInfoDAO.delete(id);
  }

  @Override
  public int update(ItemDifferentiationInfo o) {
    return itemDifferentiationInfoDAO.update(o);
  }

  @Override
  public ItemDifferentiationInfo selectById(int id) {
    return itemDifferentiationInfoDAO.selectById(id);
  }

  @Override
  public List<ItemDifferentiationInfo> selectByFields(List<String> fields) {
    return itemDifferentiationInfoDAO.selectByFields(fields);
  }

  /**
   * 根据处方 ID 查询多个条辨内容。
   */
  public List<String> selectContentsByPrescriptionId(int id) {
    List<String> contents = new ArrayList<>();

    for (ItemDifferentiationInfo oo : itemDifferentiationInfoDAO.selectByPrescriptionId(id))
      contents.add(oo.getContent());
    return contents;
  }
}
