package net.penyo.herbms.dao;

import net.penyo.herbms.pojo.ItemDifferentiationInfo;
import net.penyo.herbms.util.Pool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 条辨概要的数据访问代理
 *
 * @author Penyo
 * @see ItemDifferentiationInfo
 * @see GenericDAO
 */
@Repository
public class ItemDifferentiationInfoDAO extends GenericDAO<ItemDifferentiationInfo> {
  public ItemDifferentiationInfoDAO() {
    super("ItemDifferentiationInfoMapper");
  }

  /**
   * 根据处方 ID 查询元素。
   */
  public List<ItemDifferentiationInfo> selectByPrescriptionId(int id) {
    List<ItemDifferentiationInfo> is = new ArrayList<>();

    try (SqlSession s = Pool.getSession()) {
      is.addAll(s.selectList(fullMapperName + ".selectByPrescriptionId", id));
    }
    return is;
  }
}