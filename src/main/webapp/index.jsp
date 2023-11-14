<html>

<head>
  <%@ page pageEncoding="UTF-8" %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>HerbMS：中药处方管理系统</title>
  <%@ page import="com.penyo.herbms.dao.*" %>
  <%@ page import="com.penyo.herbms.pojo.*" %>
</head>

<body>
  <div class="box">
    <div class="title-box">
      <h1 class="title">中药处方管理系统</h1>
      <p class="subtitle">Based on JSP/JDBC</p>
    </div>
    <div class="app">
      <%
        out.println("For example, " + HerbDAO.class + " is ready.");
      %>
    </div>
  </div>
</body>

<style>
  :root {
    --c-highlight: #BE3455;
    --c-background: #fff;
    --c-text-L1: #eee;
    --c-text-L2: #333;
  }

  * {
    margin: 0;
    padding: 0;
  }

  div.box {
    position: fixed;
    width: calc(100vw - 2rem * 2);
    height: calc(100vh - 2.5rem - 2rem);
    padding: 2.5rem 2rem 2rem;
    background: var(--c-highlight);

    .title-box {
      display: flex;
      color: var(--c-text-L1);
      user-select: none;

      .title {
        margin-right: 0.3rem;
      }

      .subtitle {
        opacity: 0.3;
      }
    }

    .app {
      width: 100%;
      height: calc(100% - 2.5rem);
      background: var(--c-background);
      border-radius: 0.3rem;
      overflow-y: auto;
      box-shadow: 0 0.1rem 3px 3px rgba(0, 0, 0, .1);
    }
  }
</style>

</html>
