# 欢迎使用 HerbMS

Herb Management System：中药处方管理系统，基于灵活架构构建的 Java Web 应用程序。

## 快速开始

本项目所需依赖如下：

| 名称                                                            | 类型         | 最低版本  |
|---------------------------------------------------------------|------------|-------|
| [VS Code](https://code.visualstudio.com/#alt-downloads)       | IDE        | N/A   |
| [JDK](https://www.oracle.com/cn/java/technologies/downloads/) | 基本环境       | 17    |
| [Maven](https://maven.apache.org/download.cgi)                | 依赖管理器      | 4.0.0 |
| [Tomcat](https://tomcat.apache.org/download-10.cgi)           | Servlet 容器 | 10    |
| [MySQL](https://dev.mysql.com/downloads/mysql/)               | 数据库        | 8.0.0 |
| [Git](https://git-scm.com/download/)                          | 版本控制器      | N/A   |
| [Tsington](https://github.com/penyoofficial/Tsington)         | 连接池        | N/A   |

下图较好地概述了流程核心：

```mermaid
graph
  s1["安装全部依赖"]
  -->s2["使用 git clone 获取项目"]
  -->s3
  -->s4
  -->s5["部署 Tsington"]
  -->s6["Maven 打包"]
  -->s7["添加本地 Tomcat、添加部署并发布服务器"]
  -->s8["启动 Tomcat，访问 localhost/herbms/"]
  subgraph s3["在 VS Code 中安装插件"]
    s3.1["Extension for Java"]
    s3.2["Community Server Connector"]
    s3.3["JSP"]
  end
  subgraph s4["配置数据库"]
    s4.1["配置 src/main/java/db.properties"]
    s4.2["执行 document/ssql/herbms.sql"]
  end
```
