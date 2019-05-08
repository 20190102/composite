import $ from 'jquery'


import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import './static/css/login.css'


import Vue from 'vue/dist/vue.js'
import Vuex from 'vuex/dist/vuex.js'
import VueRouter from 'vue-router'

import Axios from 'axios'
import VueAxios from 'vue-axios'


import App from './components/app.vue'
import Login from './components/login.vue'
import Register from './components/register.vue'

import store from './store/store.js'
import setting from './static/js/axiosSetting.js'
Vue.use(VueRouter)
Vue.use(Vuex)

//是否允许 vue-devtools 检查代码。开发版本默认为 true，生产版本默认为 false。生产版本设为 true 可以启用检查。
Vue.config.devtools = true

Axios.defaults.baseURL = 'http://localhost:8080/composite';
Axios.defaults.headers.common['Authorization'] = localStorage.token;

Axios.interceptors.response.use(
  response => {
    if (response.data.token) {
        localStorage.token=response.data.token
    }
    return response
  }
)


var router = new VueRouter({
    routes: [
        { path: '/', redirect: '/login' },
        { path: '/login', component: Login },
        { path: '/register', component: Register }
    ]
})

new Vue({
    el: "#app",
    //这个方法会替换#app的标签
    render: c => c(App),
    router,
    store,
    watch: { //当路由改变时，初始化state中的数据
        '$route.path': (newVal, oldVal) => {
            store.dispatch('initMsg')
        }
    },
    mounted(){
        store.dispatch('tokenValidate')
    }
})


