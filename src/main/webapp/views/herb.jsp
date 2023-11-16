<html>

<head>
  <%@ page pageEncoding="UTF-8" %>
  <link rel="stylesheet" href="../assets/base.css">
  <%@ page import="java.util.*" %>
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

  Enumeration<String> paramNames = request.getParameterNames();
  Map<String, String> params = new HashMap<String, String>();
  while (paramNames.hasMoreElements()) {
    String key = paramNames.nextElement();
    params.put(key, request.getParameter(key));
  }

  // Query Part

  String keyword = params.get("keyword");
  if (keyword == null)
    keyword = "";

  boolean isId = false;
  String oIsId = params.get("isId");
  if (oIsId != null)
    isId = oIsId.equals("on");

  boolean needQueryHerb = true;
  String oNeedQueryHerb = params.get("needQueryHerb");
  if (oNeedQueryHerb != null)
    needQueryHerb = oNeedQueryHerb.equals("1");

  // Update Part

  String oId = params.get("id");
  if (oId != null) {
    String opType = params.get("opType");
    try {
      int id = Integer.parseInt(oId);
      if (opType == null) {
        if (needQueryHerb) {
          HerbBean h = new HerbBean(
            id,
            Integer.parseInt(params.get("code")),
            params.get("name"),
            params.get("nickname"),
            params.get("type"),
            params.get("description"),
            params.get("efficacy"),
            params.get("taste"),
            params.get("origin"),
            params.get("dosage"),
            params.get("taboo"),
            params.get("processing"));
          if (hDAO.select(id) == null)
            hDAO.add(h);
          else
            hDAO.update(h);
        }
        else {
          ExperienceBean exp = new ExperienceBean(
            id,
            Integer.parseInt(params.get("herbId")),
            params.get("derivation"),
            params.get("content"));
          if (expDAO.select(id) == null)
            expDAO.add(exp);
          else
            expDAO.update(exp);
        }
      }
      else if (opType.equals("delete")) {
        if (needQueryHerb)
          hDAO.delete(id);
        else
          expDAO.delete(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      response.sendRedirect(request.getRequestURI());
    }
  }

  // Arbitrate Time

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
        ['唯一识别码', 'id'],
        ['编号', 'code'],
        ['学名', 'name'],
        ['别名', 'nickname'],
        ['归属类别', 'type'],
        ['本经原文', 'description'],
        ['主治', 'efficacy'],
        ['性味', 'taste'],
        ['产地', 'origin'],
        ['用量', 'dosage'],
        ['禁忌', 'taboo'],
        ['炮制方法', 'processing'],
      ], [
        ['唯一识别码', 'id'],
        ['中草药 ID', 'herbId'],
        ['出处', 'derivation'],
        ['心得内容', 'content'],
      ]])
      const isId = ref(<%= isId %>)
      const needQueryHerb = ref(<%= needQueryHerb %>)
      const objs = ref(JSON.parse((() => {
        if (isId.value && needQueryHerb.value)
          return '[<%= hObj %>]'
        else if (isId.value && !needQueryHerb.value)
          return '[<%= expObj %>]'
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
    },
    methods: {
      handleNewOrCancelOP () {
        this.isNewingFormPoped = !this.isNewingFormPoped
        document.querySelector('.infos').querySelectorAll('input').forEach((i, index) => {
          i.value = ""
        })
      },
      handleAlterOP(id) {
        this.isNewingFormPoped = true

        let obj = null;
        this.objs.forEach((o) => {
          if (id === o.id)
            obj = Object.values(o);
        })

        document.querySelector('.infos').querySelectorAll('input').forEach((i, index) => {
          i.value = obj[index]
        })
      },
      handleDeleteOP(id) {
        const url = `${window.location.origin}${window.location.pathname}?opType=delete&needQueryHerb=${this.needQueryHerb ? 1 : 0}&id=${id}`
        window.location.href = url
      },
    }
  }).mount('#subapp')
</script>

<body>
  <div id="subapp">
    <div class="tool-bar">
      <div class="flex-left">
        <button @click="handleNewOrCancelOP">
          {{ isNewingFormPoped ? '取消' : '新增' }}
        </button>
      </div>
      <form class="flex-right">
        <input type="text" name="keyword">
        <input type="checkbox" name="isId" id="isId">
        <label for="isId">ID 查询</label>
        <button type="submit" name="needQueryHerb" value="1">查询中草药</button>
        <button type="submit" name="needQueryHerb" value="0">查询心得</button>
      </form>
    </div>
    <div class="line"></div>
    <div v-show="isNewingFormPoped" class="dialog">
      <form class="row-edit">
        <p class="tip">若处于“新增”模式，则对唯一识别码的指定无效。</p>
        <input class="invisible" type="text" name="needQueryHerb" :value="needQueryHerb ? 1 : 0">
        <table class="infos">
          <tr v-for="ch in columnHeads[(needQueryHerb ? 0 : 1)]">
            <td><label class="label" :for="ch[1]">{{ ch[0] }}</label></td>
            <td><input type="text" :name="ch[1]" :id="ch[1]" required></td>
          </tr>
        </table>
        <input type="submit">
      </form>
    </div>
    <table class="result">
      <thead>
        <tr>
          <th v-for="ch in columnHeads[(needQueryHerb ? 0 : 1)]" :style="`min-width: ${ch[0].length}rem;`">{{ ch[0] }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="o in objs">
          <td v-for="col in o">{{ col }}</td>
          <td>
            <button @click="handleAlterOP(o.id)">改</button>
          </td>
          <td>
            <button @click="handleDeleteOP(o.id)">删</button>
          </td>
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

  p.tip {
    font-style: italic;
    font-size: 0.8rem;
  }

  .invisible {
    display: none;
  }
  
  .infos {
    margin-bottom: 1rem;
  }
  
  .label {
    margin-right: 1rem;
  }
</style>

</html>