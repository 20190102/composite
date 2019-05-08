<template>
    <div>
        <input type="text" v-model="email" class="form-control" placeholder="邮箱" @blur="testEmail" required autofocus>
        <label class="errortip">{{emailMsg}}</label>
        <input type="password" v-model="password" class="form-control" placeholder="密码" @blur="testPwd" id="password" required>
        <label class="errortip">{{pwdMsg}}</label>
        <div class="checkbox">
            <label> <input type="checkbox" value="remember-me" @click="checkbox=!checkbox">
                记住我(7天)
            </label>
            <a href="" class="pull-right">忘记密码</a>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" @click="login">登录</button>
    </div>
</template>
<script>
import { mapState } from 'vuex'
export default {
    computed: mapState(['emailMsg', 'pwdMsg', 'emailFlag', 'pwdFlag']),
    data() {
        return {
            email: '',
            password: '',
            checkbox:false
        }
    },
    methods: {
        testEmail() {
            this.$store.dispatch('testEmail', this.email)
        },
        testPwd() {
            this.$store.dispatch('testPwd', this.password)
        },
        login() {
            if (this.emailFlag && this.pwdFlag) {
                this.$store.dispatch('login', { email: this.email, password: this.password,
                remember:this.checkbox })
            }
        }
    }

}
</script>
<style>
</style>