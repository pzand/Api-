# 使用
- 修改api-backend的mysql, redis的连接地址, dubbo的注册中心地址
- 修改api-gateway的dubbo的注册中心地址
- 启动所有项目

> 不同的项目保存在不同的分支。


# 项目构成
- **api-backend**: API开放平台的后端，用于管理统计用户，接口。
- **api-common**: API开放平台的通用接口、Model，为Dubbo RPC远程调用而设立
- **api-frontend**: API开放平台的前端项目，用于浏览接口，管理接口，开通接口调用权限等。
- **api-gateway**: API网关，所有的接口调用都会通过API网关进行路由转发。API网关会统一进行用户验证，签名认证，流量染色，验证通过后会进行路由的转发

# 模拟接口
- **api-interface**: 模拟接口，用于测试调用
- **cline-sdk**: 模拟接口SDK，用于快速调用模拟接口。透明化了接口调用，无需关心API的签名认证