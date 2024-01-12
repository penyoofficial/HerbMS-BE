package net.penyo.herbms.service;

import net.penyo.herbms.pojo.Herb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 中草药的业务代理
 *
 * @author Penyo
 * @see Herb
 * @see GenericService
 */
@Service
public class HerbService extends GenericService<Herb> {
  @Override
  public int add(Herb o) {
    return herbDAO.add(o);
  }

  @Override
  public int delete(int id) {
    return herbDAO.delete(id);
  }

  @Override
  public int update(Herb o) {
    return herbDAO.update(o);
  }

  @Override
  public Herb selectById(int id) {
    return herbDAO.selectById(id);
  }

  @Override
  public List<Herb> selectByFields(List<String> fields) {
    return herbDAO.selectByFields(fields);
  }

  /**
   * 根据中草药使用心得 ID 查询单个中草药名称。
   */
  public String selectNameByExperienceId(int id) {
    return herbDAO.selectByExperienceId(id).getName();
  }

  /**
   * 根据处方 ID 查找多个中草药名称和解释。
   */
  public List<String> selectNamesAndDescriptionsByPrescriptionId(int id) {
    List<String> names = new ArrayList<>();

    for (Herb o : herbDAO.selectByPrescriptionId(id))
      names.add(o.getName() + "/" + o.getDescription());
    return names;
  }
}
