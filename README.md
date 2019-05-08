# composite
## 一个综合的练习项目  
前端: bootstrap,vue,vuex,axios  
后端: SSM mysql redis  
前后端分离项目，所有数据交互都使用axios实现  
前后端是分开开发的，所以在后端添加了跨域注解

* 基础的RESTful风格的crud,分页
* 向邮箱发送验证码，注册验证，配合redis实现验证码有效时长
* 使用jwt实现单点登录和验证是否已登录，密钥存在redis中，可以定时更换
* 主页引用的js文件经过webpack打包
  
