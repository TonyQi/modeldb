下载完后端代码后，记得先安装`lombok插件`，否则你的IDE会报代码缺失。
## 后端工程启动
### 环境须知
- mysql一个，redis一个
- jdk1.8
- IDE插件一个，`lombok插件`，具体百度即可

### 运行步骤
- 运行数据库脚本：依次运行数据库：ace-admin/db/init.sql、ace-auth-server/db/init.sql
- 修改配置数据库配置：ace-admin/src/main/resources/application.yml、ace-gate/src/main/resources/application.yml
- 按`顺序`运行main类：CenterBootstrap（ace-center）、ConfigServerBootstrap（ace-config）、AuthBootstrap（ace-auth-server）、AdminBootstrap（ace-admin）、GateBootstrap（ace-gate）

### 项目结构
```
├─ace-security
│  │  
│  ├─ace-admin----------------管理端服务层
│  │ 
│  ├─ace-auth-----------------鉴权中心
│  │ 
│  ├─ace-gate-----------------网关负载中心
│  │ 
│  ├─ace-center---------------服务注册中心  
│  │
│  ├─ace-api------------------公共服务接口包
```
----

## 前端工程启动[AG-Admin-UI][地址](https://gitee.com/geek_qi/AG-Admin-v2.0)
### 环境搭建
```
node 版本：v6.11.2
npm 版本：3.10.10
```
### 开发
 
```bash
   
    # 安装依赖
    npm install
    //or # 建议不要用cnpm  安装有各种诡异的bug 可以通过如下操作解决npm速度慢的问题
    npm install --registry=https://registry.npm.taobao.org

    # 本地开发 开启服务
    npm run dev
```
浏览器访问 http://localhost:9527

### 发布
```bash
    # 发布测试环境 带webpack ananalyzer
    npm run build:sit-preview

    # 构建生成环境
    npm run build:prod
```

### 目录结构
```shell
├── build                      // 构建相关  
├── config                     // 配置相关
├── src                        // 源代码
│   ├── api                    // 所有请求
│   ├── assets                 // 主题 字体等静态资源
│   ├── components             // 全局公用组件
│   ├── directive              // 全局指令
│   ├── filtres                // 全局filter
│   ├── mock                   // mock数据
│   ├── router                 // 路由
│   ├── store                  // 全局store管理
│   ├── styles                 // 全局样式
│   ├── utils                  // 全局公用方法
│   ├── view                   // view
│   ├── App.vue                // 入口页面
│   └── main.js                // 入口 加载组件 初始化等
├── static                     // 第三方不打包资源
│   └── Tinymce                // 富文本
├── .babelrc                   // babel-loader 配置
├── eslintrc.js                // eslint 配置项
├── .gitignore                 // git 忽略项
├── favicon.ico                // favicon图标
├── index.html                 // html模板
└── package.json               // package.json

```