import Vue from 'vue/dist/vue.js'
import Axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, Axios)


export default {
    testEmail({ commit }, email) {
        const regExp = /^[0-9a-zA-Z-_]+@[a-zA-Z]+\.[a-zA-Z]{2,5}(\.[a-zA-Z]{2,5}){0,1}$/
        if (email === '') {
            commit('testEmail', { msg: '请输入邮箱' })
        } else if (!regExp.test(email)) {
            commit('testEmail', { msg: '邮箱格式错误' })
        } else {
            commit('testEmail', { msg: '' })
        }
    },
    testPwd({ commit }, password) {
        if (password === '') {
            commit('testPwd', { msg: '请输入密码' })
        } else if (password.length < 6) {
            commit('testPwd', { msg: '密码不能小于6个字符' })
        } else if (password.length > 16) {
            commit('testPwd', { msg: '密码不能大于16个字符' })
        } else {
            commit('testPwd', { msg: '' })
        }
    },
    initMsg({ commit }) {
        commit('initMsg')
    },
    login({ commit }, msg) {
        Vue.axios.post('login',
            {
                email: msg.email,
                password: msg.password,
                remember: msg.remember
            }
        ).then(result => {
            if (result.data.code===200) {
                window.location.href = 'static/page/userList.html';
            } 
            else if(result.data.code===401) {
                commit('msg', { msg: '邮箱或密码错误' })
            }
        })
    },
    register({ commit }, msg) {
        Vue.axios.post('register', {
            email: msg.email,
            username: msg.uname,
            password: msg.password,
            verificationCode:msg.verificationCode
        }).then(result => {
            if (result.data) {
                commit('msg', { msg: '注册成功请登录' })
            } else {
                commit('msg', { msg: '注册失败' })
            }
        })
    },
    tokenValidate({ commit }) {
        Vue.axios.post('loginInit', {}).then(result => {
            if (result.data.code===200) {
                window.location.href = 'static/page/userList.html';
            }
        })
    }

}