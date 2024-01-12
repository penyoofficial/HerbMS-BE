package net.penyo.herbms.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 应用配置
 *
 * @author Penyo
 */
@Configuration
@ComponentScan("net.penyo.herbms")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}