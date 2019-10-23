
## 简介
这是一个基于Springboot2.x，vue2.x的前后端分离的开源博客系统，提供 前端界面+管理界面+后台服务 的整套系统源码。响应式设计，手机、平板、PC，都有良好的视觉效果！

- 你可以拿它作为前端Vue2.x学习的练手教程；
- 你也可以把它作为springboot2.x技术的学习项目；
- 你也可以拿它作为当下火热的ElasticSearch和RabbitMQ的学习Demo;
- 你也可以将其视为一个前后端分离的项目实践；
- 你还可以作为SpringCloud服务化思想的学习理解；
- ...
## 使用技术
- SpringBoot 2.x 后台基本框架
- Vue 2.x 前端基本框架
- ElementUI：后台管理页面UI库
- IView：前端UI库
- ElasticSearch 搜索层
- RabbitMQ 消息队列
- Shiro 鉴权层
- Redis 缓存层
- Swagger 文档
- Mybaits-Plus 好用的mybatis框架
- lombox getter setter插件
- druid 数据库连接池
- jasypt 加密
- 七牛云 图床

## 站点演示
[http://39.105.211.155](http://39.105.211.155)

## 模块分层
### 后端模块
```shell
dbblog
├── dbblog-auth   # 鉴权模块：shiro
│   ├── pom.xml
│   └── src
├── dbblog-core   # 核心模块：配置文件，Entity类，mapper类，工具类，异常过滤等
│   ├── pom.xml
│   └── src
├── dbblog-manage # 后台管理界面Service
│   ├── pom.xml
│   └── src
├── dbblog-portal # 前端界面Service
│   ├── pom.xml
│   └── src
├── dbblog-search # 搜索模块：elasticSearch
│   ├── pom.xml
└── └── src
├── dbblog-common # 工具类模块
│   ├── pom.xml
└── └── src
```
### 后台依赖关系

dbblog-common -> dbblog-core -> dbblog-auth -> dbblog-manage -> dbblog-portal -> dbblog-search
- 采用多模块的形式，便于后续SpringCloud微服务的改造升级

### 前端模块
#### 后台管理页面
```shell
├── assets
├── components # 公共组件
├── element-ui
├── element-ui-theme # elementUI主题
├── icons   
├── router  # 路由
├── store   # vuex
├── utils   # js工具类
└── views   
    ├── common # 公共模块
    └── modules
        ├── article    # 文章模块
        ├── book       # 阅读模块
        ├── operation  # 运维模块
        └── sys        # 系统模块


```
#### 前台页面
```shell
├── assets
├── common
├── components
│   ├── content # 页面
│   │   ├── ArticleContent.vue      # 文章详情页
│   │   ├── ArticleListContent.vue  # 文章列表页
│   │   ├── BookContent.vue         # 图书详情页
│   │   ├── BookListContent.vue     # 图书列表页
│   │   ├── BookNoteContent.vue     # 笔记详情页
│   │   ├── HomeContent.vue         # 首页
│   │   ├── SearchResult.vue        # 搜索结果页
│   │   └── TimeLineContent.vue     # 归档页
│   ├── footer
│   ├── header
│   ├── index
│   ├── utils
│   └── views # 页面组件库
│       ├── Archive 
│       ├── Article
│       ├── Book
│       ├── BookNote
│       ├── Classify
│       └── TimeLine
├── router # 路由
├── store  # Vuex
└── utils  # js工具类

```
## 项目部署
### 服务端
项目后端环境
- JDK1.8
- Mysql5.7
- Redis
- IDEA编译器
- Lombox插件（百度一下）
- ElasticSearch 6.x
- RabbitMQ
- IDEA编译器

部署步骤：
1. 创建数据库dbblog，并导入dbblog-backend -> db里的所有sql文件
2. 修改dbblog-backend -> dbblog-> dbblog-core里的application-*.yml的数据库连接、redis连接、ElasticSearch连接、RabbitMQ连接
3. 导入项目，并且运行dbblog-backend -> dbblog-search -> BlogApplication里的main方法

### 前端
前端环境：
- Node.js 8.0+
- WebStorm编辑器

部署步骤：
1. 导入项目，运行 npm install（如果失败，清空包后试试cnpm install）
2. 启动项目：npm run dev
3. 前端地址：localhost:8002 管理界面地址：localhost:8888  账号admin，密码123456

