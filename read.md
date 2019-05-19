# composite
## 一个查看用户数据的综合练习项目  
前端: bootstrap,vue,vue-router,vuex,axios  
后端: SSM,mysql,redis  

## 主要功能:
1. 登录注册，注册时向邮箱发送验证码，配合redis实现验证码有效时长。
2. 使用jwt生成的token实现登录验证，配合cookie实现单点登录，jwt密钥保存在redis，可定时更换。
3. REST风格的CRUD接口，pagehelper插件实现分页查看，搜索（全字段模糊匹配）。
4. 上传xlsx格式的excel文件，保存在redis，前端可以分页查看，暂时没有其他功能。
