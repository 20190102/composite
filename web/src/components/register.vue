<template>
    <div>
        <input type="text" v-model="email" @blur="testEmail" class="form-control" placeholder="邮箱" required autofocus>
        <label class="errortip">{{emailMsg}}</label>
        <input type="text" v-model='uname' @blur="testUname" class="form-control" placeholder="用户名(1~10个字符，可包含-_@.)" required>
        <label class="errortip">{{unameMsg}}</label>
        <input type="password" v-model='password' @blur="testPwd" class="form-control" placeholder="密码(6~16个字符，区分大小写)" required>
        <label class="errortip">{{pwdMsg}}</label>
        <div class="input-group">
            <input type="text" v-model="verificationCode" class="form-control" placeholder="验证码">
            <span class="input-group-btn">
                <button type="button" class="btn btn-default" title="向邮箱发送验证码" id="sendCode" @click="sendCode">{{send}}</button>
            </span>
        </div>
        <button id="register" type="button"  class="btn btn-lg btn-primary btn-block" @click="register">注册</button>
    </div>
</template>
<script>
import Vue from 'vue/dist/vue.js'
import Axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, Axios)
import { mapState } from 'vuex'
export default {
    computed: mapState(['emailMsg', 'pwdMsg', 'pwdFlag', 'emailFlag']),
    data() {
        return {
            email: '',
            uname: '',
            password: '',
            unameMsg: '',
            unameFlag: false,
            verificationCode:'',
            send: '发送验证码'
        }
    },
    methods: {
        testEmail() {
            this.$store.dispatch('testEmail', this.email)
        },
        testPwd() {
            this.$store.dispatch('testPwd', this.password)
        },
        testUname() {
            const reg = /^[-_@\.0-9a-zA-Z\u4e00-\u9fa5]{1,10}$/
            if (this.uname === '') {
                this.unameMsg = '请输入用户名'
            } else if (this.uname.length > 10) {
                this.unameMsg = '用户名长度不可大于10位'
            } else if (!reg.test(this.uname)) {
                this.unameMsg = '用户名不可包含-_@.以外的特殊字符'
            } else {
                this.unameFlag = true
                this.unameMsg = ''
            }

        },
        register() {
            if (this.emailFlag && this.unameFlag && this.pwdFlag) {
                this.$store.dispatch('register', { 
                    email: this.email, 
                    uname: this.uname, 
                    password: this.password ,
                    verificationCode:this.verificationCode
                })
            }
        },
        sendCode() {
            if (this.emailFlag) {
                Vue.axios.post('sendCode', {
                    email: this.email,
                }).then(result => {
                    if (result.data) {
                        this.send = "发送成功"
                    } else {
                        this.send = "发送失败"
                    }
                })
            }

        }
    }
}
</script>
<style>
</style>