<html>

<head>
  <%@ page pageEncoding="UTF-8" %>
  <link rel="stylesheet" href="./assets/base.css">
  <%@ page import="java.util.*" %>
</head>

<%
  String servletName = "prescriptionServlet";

  boolean needQueryA = true;
  Object oNeedQueryA = session.getAttribute("needQueryA");
  session.setAttribute("needQueryA", null);
  if (oNeedQueryA != null)
    needQueryA = (Boolean) oNeedQueryA;

  int affectedRows = -1;
  Object oAffectedRows = session.getAttribute("affectedRows");
  session.setAttribute("affectedRows", null);
  if (oAffectedRows != null)
    affectedRows = (int) oAffectedRows;

  List<?> list = new ArrayList<>();
  Object oList = session.getAttribute("list");
  session.setAttribute("list", null);
  if (oList != null)
    list = (List<?>) oList;
  else
    response.sendRedirect(servletName);
%>

<script type="module">
  import { createApp, ref } from './assets/vue.esm-browser.prod.js'

  createApp({
    setup() {
      const isNewingFormPoped = ref(false);
      const columnHeads = ref([[
        ['唯一识别码', 'id'],
        ['名称', 'name'],
        ['别名', 'nickname'],
        ['解释', 'description'],
      ], [
        ['唯一识别码', 'id'],
        ['中药处方 ID', 'prescriptionId'],
        ['中药 ID', 'herbId'],
        ['用量', 'dosage'],
        ['用法', 'usage'],
      ]])
      const needQueryA = ref(<%= needQueryA %>);
      const objs = ref(JSON.parse('<%= list %>'));
      const servletName = ref('<%= servletName %>');
      const opType = ref('');

      if (!<%= affectedRows %>)
        alert('操作失败！可能是因为不合法的请求参数。');

      return {
        isNewingFormPoped,
        columnHeads,
        needQueryA,
        objs,
        servletName,
        opType,
      }     
    },
    methods: {
      handleNewOrCancelOP () {
        this.opType = 'add';
        this.isNewingFormPoped = !this.isNewingFormPoped;

        document.querySelector('.infos').querySelectorAll('input').forEach((i) => {
          i.value = "";
        })
      },
      handleAlterOP(id) {
        this.opType = 'update';
        this.isNewingFormPoped = true;

        let obj = null;
        this.objs.forEach((o) => {
          if (id === o.id)
            obj = Object.values(o);
        })

        document.querySelector('.infos').querySelectorAll('input').forEach((i, index) => {
          i.value = obj[index];
        });
      },

      handleDeleteOP(id) {
        this.opType = 'delete';

        const path = `${window.location.origin}${window.location.pathname}`;
        const url = `${path.substring(0, path.lastIndexOf('/'))}/${this.servletName}?opType=delete&needQueryA=${this.needQueryA ? 1 : 0}&id=${id}`;
        window.location.href = url;
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
      <form class="flex-right" :action="servletName">
        <input type="text" name="keyword">
        <input type="checkbox" name="isId" id="isId">
        <label for="isId">ID 查询</label>
        <button type="submit" name="needQueryA" value="1">查询处方概要</button>
        <button type="submit" name="needQueryA" value="0">查询处方</button>
      </form>
    </div>
    <div class="line"></div>
    <div v-show="isNewingFormPoped" class="dialog">
      <form class="row-edit" :action="servletName">
        <input class="invisible" type="text" name="needQueryA" :value="needQueryA ? 1 : 0">
        <input class="invisible" type="text" name="opType" :value="opType">
        <p class="tip">若处于“新增”模式，则对唯一识别码的指定无效。</p>
        <table class="infos">
          <tr v-for="ch in columnHeads[(needQueryA ? 0 : 1)]">
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
          <th v-for="ch in columnHeads[(needQueryA ? 0 : 1)]" :style="`min-width: ${ch[0].length}rem;`">{{ ch[0] }}</th>
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
  
  .infos {
    margin-bottom: 1rem;
  }
  
  .label {
    margin-right: 1rem;
  }

</style>

</html>