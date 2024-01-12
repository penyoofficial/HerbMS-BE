package net.penyo.herbms.dao;

import net.penyo.herbms.pojo.ItemDifferentiation;
import org.springframework.stereotype.Repository;

/**
 * 条辨的数据访问代理
 *
 * @author Penyo
 * @see ItemDifferentiation
 * @see GenericDAO
 */
@Repository
public class ItemDifferentiationDAO extends GenericDAO<ItemDifferentiation> {
  public ItemDifferentiationDAO() {
    super("ItemDifferentiationMapper");
  }
}
