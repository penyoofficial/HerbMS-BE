package com.penyo.herbms.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SQL 会话池
 *
 * @author Penyo
 */
public class SessionPool {
  /**
   * 公共连接池
   */
  private static final SqlSessionFactory pool = new SqlSessionFactoryBuilder().build(SessionPool.class.getClassLoader().getResourceAsStream("mybatis.config.xml"));

  /**
   * 获取 SQL 会话。
   */
  public static SqlSession getSession() {
    return pool.openSession();
  }
}
