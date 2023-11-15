<html>

<head>
  <%@ page pageEncoding="UTF-8" %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="../assets/base.css">
  <%@ page import="java.util.List" %>
  <%@ page import="com.penyo.herbms.dao.*" %>
  <%@ page import="com.penyo.herbms.pojo.*" %>
</head>

<%
  HerbDAO hDAO = new HerbDAO();
  ExperienceDAO expDAO = new ExperienceDAO();

  List<HerbBean> hs = null;
  List<ExperienceBean> exps = null;
  HerbBean hObj = null;
  ExperienceBean expObj = null;

  String keyword = request.getParameter("keyword");
  if (keyword == null)
    keyword = "";

  boolean isId = false;
  String oIsId = request.getParameter("isId");
  if (oIsId != null)
    isId = oIsId.equals("on");

  boolean needQueryHerb = true;
  String oNeedQueryHerb = request.getParameter("needQueryHerb");
  if (oNeedQueryHerb != null)
    needQueryHerb = oNeedQueryHerb.equals("1");

  if (isId) {
    if (needQueryHerb)
      hObj = hDAO.select(Integer.parseInt(keyword));
    else
      expObj = expDAO.select(Integer.parseInt(keyword));
  }
  else {
    if (needQueryHerb)
      hs = hDAO.selectByField(keyword);
    else
      exps = expDAO.selectByField(keyword);
  }
%>

<script type="module">
  import { createApp, ref } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.prod.js'

  createApp({
    setup() {
      const isNewingFormPoped = ref(false)
      const columnHeads = ref([[
        '唯一识别码',
        '编号',
        '学名',
        '别名',
        '归属类别',
        '本经原文',
        '主治',
        '性味',
        '产地',
        '用量',
        '禁忌',
        '炮制方法',
      ], [
        '唯一识别码',
        '中草药',
        '出处',
        '心得内容',
      ]])
      const isId = ref(<%= isId %>)
      const needQueryHerb = ref(<%= needQueryHerb %>)
      const objs = ref(JSON.parse((() => {
        if (isId.value && needQueryHerb.value)
          return '<%= hObj %>'
        else if (isId.value && !needQueryHerb.value)
          return '<%= expObj %>'
        else if (!isId.value && needQueryHerb.value)
          return '<%= hs %>'
        else if (!isId.value && !needQueryHerb.value)
          return '<%= exps %>'
        return '[]'
      })()))

      return {
        isNewingFormPoped,
        columnHeads,
        needQueryHerb,
        isId,
        objs,
      }
    }
  }).mount('#subapp')
</script>

<body>
  <div id="subapp">
    <div class="tool-bar">
      <div class="flex-left">
        <button @click="isNewingFormPoped = !isNewingFormPoped">
          {{ isNewingFormPoped ? '取消' : '新增' }}
        </button>
      </div>
      <form class="flex-right">
        <input type="text" name="keyword">
        <input type="checkbox" name="isId" id="isId">
        <label for="isId">ID 查询</label>
        <button type="submit" name="needQueryHerb" value="1" @click="needQueryHerb = true">查询中草药</button>
        <button type="submit" name="needQueryHerb" value="0" @click="needQueryHerb = false">查询心得</button>
      </form>
    </div>
    <div class="line"></div>
    <table>
      <thead>
        <tr>
          <th v-for="ch in columnHeads[(needQueryHerb ? 0 : 1)]" :style="`min-width: ${ch.length}rem;`">{{ ch }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="isId">
          <td v-for="col in objs">{{ col }}</td>
        </tr>
        <tr v-else v-for="o in objs">
          <td v-for="col in o">{{ col }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</body>

<style>
  #subapp {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  div.tool-bar {
    display: flex;

    .flex-right {
      flex: 5;
    }
  }
</style>

</html>