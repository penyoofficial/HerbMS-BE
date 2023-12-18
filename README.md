# 欢迎使用 HerbMS

Herb Management System：中药处方管理系统，基于灵活架构构建的 Jakarta EE 应用程序。

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
| [HerbMS-Vue](https://github.com/penyoofficial/HerbMS-Vue)     | 前端         | N/A   |

下图较好地概述了流程核心：

```mermaid
graph
    s1["安装全部依赖"]
    s2["使用 git clone 获取项目"]
    subgraph s3["在 VS Code 中安装插件（可选）"]
        s3.1["Extension for Java"]
        s3.2["Community Server Connector"]
        s3.3["JSP"]
    end
    subgraph s4["配置数据库"]
        s4.1["配置 src/main/resources/db.config.yml"]
        s4.2["执行 src/main/sql/herbms.sql"]
    end
    s5["Maven 打包"]
    s6["添加本地 Tomcat、添加部署并发布服务器"]
    s7["启动 Tomcat，启动前端"]
    s1 --> s2 --> s3 --> s4 --> s5 --> s6 --> s7
```

## 模块设计

HerbMS（后端）主要由四部分构成：数据容器（Bean）、数据访问代理（DAO）、业务代理（Service）和请求处理代理（Servlet），下图清晰地展示了各模块间的关系：

```mermaid
graph
    subgraph Entire Project
        subgraph Servlet
            4a["abstract class Generic"]
        end
        subgraph Service
            3i["interface Abstract"]
            3a["abstract class Generic"]
            3a -- " impl " --> 3i
        end
        subgraph DAO
            2i["interface Abstract"]
            2a["abstract class Generic"]
            2a -- " impl " --> 2i
        end
        subgraph Bean
            1i["interface Abstract"]
            1a["abstract class Generic"]
            1a -- " impl " --> 1i
        end
        3a -- " 泛型约束 " --> 4a
        1a -- " 泛型约束 " --> 4a
        3i -- " extends " --> 2i
        2a -- " 泛型约束 " --> 3i
        1a -- " 泛型约束 " --> 3i
        1a -- " 泛型约束 " --> 2i
    end
```
