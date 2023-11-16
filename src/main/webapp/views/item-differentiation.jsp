<html>

<head>
  <%@ page pageEncoding="UTF-8" %>
  <link rel="stylesheet" href="../assets/base.css">
  <%@ page import="java.util.*" %>
  <%@ page import="com.penyo.herbms.dao.*" %>
  <%@ page import="com.penyo.herbms.pojo.*" %>
</head>

<%
  ItemDifferentiationDAO itdDao = new ItemDifferentiationDAO();
  ItemDifferentiationInfoDAO itdiDao = new ItemDifferentiationInfoDAO();

  List<ItemDifferentiationBean> itds = null;
  List<ItemDifferentiationInfoBean> itdis = null;
  ItemDifferentiationBean itdObj = null;
  ItemDifferentiationInfoBean itdiObj = null;

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

  boolean needQueryInfo = true;
  String oNeedQueryInfo = params.get("needQueryInfo");
  if (oNeedQueryInfo != null)
    needQueryInfo = oNeedQueryInfo.equals("1");

  // Update Part

  String oId = params.get("id");
  if (oId != null) {
    String opType = params.get("opType");
    try {
          int id = Integer.parseInt(oId);
      if (opType == null) {
        if (needQueryInfo) {
          ItemDifferentiationInfoBean itdi = new ItemDifferentiationInfoBean(
            id,
            Integer.parseInt(params.get("code")),
            params.get("content"),
            params.get("annotation"));
          if (itdiDao.select(id) == null)
            itdiDao.add(itdi);
          else
            itdiDao.update(itdi);
          
        }
        else {
          ItemDifferentiationBean itd = new ItemDifferentiationBean(
            id,
            Integer.parseInt(params.get("itemDifferentionId")),
            Integer.parseInt(params.get("prescriptionId")),
            params.get("type"));
            

          if (itdDao.select(id) == null)
            itdDao.add(itd);
          else
            itdDao.update(itd);
        }
      }
      else if(opType.equals("delete")){
        if (needQueryInfo)
          itdDao.delete(id);
        else
          itdiDao.delete(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      response.sendRedirect(request.getRequestURI());
    }
  } 

  // Arbitrate Time

  if (isId) {
    if (needQueryInfo)
      itdObj = itdDao.select(Integer.parseInt(keyword));
    else
      itdiObj = itdiDao.select(Integer.parseInt(keyword));
  }
  else {
    if (needQueryInfo)
      itds = itdDao.selectByField(keyword);
    else
      itdis = itdiDao.selectByField(keyword);
  }
  
%>

<script type="module">
  import { createApp, ref } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.prod.js'

  createApp({
    setup() {
      const isNewingFormPoped = ref(false)
      const columnHeads = ref([[
        ['唯一识别码', 'id'],
        ['条辩ID', 'itemDifferentionId'],
        ['处方ID', 'prescriptionId'],
        ['类型', 'type'],
      ],[
        ['唯一识别码', 'id'],
        ['编号', 'code'],
        ['内容', 'content'],
        ['注释','annotation'],
      ]])
      const isId = ref(<%= isId %>)
      const needQueryInfo = ref(<%= needQueryInfo %>)
      const objs = ref(JSON.parse((() => {
        if (isId.value && needQueryInfo.value)
          return '[<%= itdObj %>]'
        else if (isId.value && !needQueryInfo.value)
          return '[<%= itdiObj %>]'
        else if (!isId.value && needQueryInfo.value)
          return '<%= itds %>'
        else if (!isId.value && !needQueryInfo.value)
          return '<%= itdis %>'
        return '[]'
      })()))

      return {
        isNewingFormPoped,
        columnHeads,
        needQueryInfo,
        isId,
        objs,
      }
    },
    methods:{
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
        const url = `${window.location.origin}${window.location.pathname}?opType=delete&needQueryInfo=${this.needQueryInfo ? 1 : 0}&id=${id}`
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
          <button type="submit" name="needQueryInfo" value="0">查询条辩</button>
          <button type="submit" name="needQueryInfo" value="1">查询条辩概要</button>
        </form>
      </div>
      <div class="line"></div>
      <div v-show="isNewingFormPoped" class="dialog">
        <form class="row-edit">
          <p class="tip">若处于“新增”模式，则对唯一识别码的指定无效。</p>
          <input class="invisible" type="text" name="needQueryInfo" :value="needQueryInfo ? 1 : 0">
          <table class="infos">
            <tr v-for="ch in columnHeads[(needQueryInfo ? 0 : 1)]">
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
            <th v-for="ch in columnHeads[(needQueryInfo ? 0 : 1)]" :style="`min-width: ${ch[0].length}rem;`">{{ ch[0] }}</th>
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