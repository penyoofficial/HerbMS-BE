package com.penyo.herbms.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 质量评价
 *
 * @author Penyo
 */
@Retention(RetentionPolicy.SOURCE)
public @interface QualityEvaluation {
  /**
   * 评价类型
   */
  enum EvaluationType {
    /**
     * “天才！”
     */
    GENIUS,
    /**
     * “优秀！”
     */
    EXCELLENT,
    /**
     * “好！”
     */
    GOOD,
    /**
     * “一般。”
     */
    AVERAGE,
    /**
     * “差。”
     */
    POOR,
    /**
     * “恶心。”
     */
    SUCK,
    /**
     * “重做。”
     */
    REBUILD,
  }

  /**
   * 评价内容
   */
  EvaluationType value();

  /**
   * 警告阈值
   */
  EvaluationType warningThreshold() default EvaluationType.AVERAGE;
}
