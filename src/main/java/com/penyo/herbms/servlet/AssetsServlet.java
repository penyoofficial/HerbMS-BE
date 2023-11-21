package com.penyo.herbms.servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 资源的请求处理层
 *
 * @author Penyo
 */
@WebServlet(name = "AssetsServlet", urlPatterns = "/assets/*")
public class AssetsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String assetPath = req.getPathInfo();
    if (assetPath != null) {
      Path path = Paths.get("/WEB-INF/assets" + assetPath);
      byte[] assetBytes = Files.readAllBytes(path);
      resp.getOutputStream().write(assetBytes);
    } else resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
  }
}
