package com.penyo.herbms.util;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 跨域资源共享过滤器
 *
 * @author Penyo
 */
@WebFilter("/*")
public class CORSFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    resp.setHeader("Access-Control-Allow-Origin", "*");
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
