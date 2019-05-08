<template>
    <form class="form-signin " novalidate="true">
        <ul class=" nav nav-pills lg">
            <li class="active" data-toggle="pill">
                <router-link to="/login">登录</router-link>
            </li>
            <li data-toggle="pill">
                <router-link to="/register">注册</router-link>
            </li>
        </ul>
        <div class="text-center">
            <label class="errortip center-block" id="msg" >{{msg}}</label>
        </div>
            
        <router-view></router-view>
    </form>
</template>
<script>
    import { mapState } from 'vuex'
    export default {
        computed: mapState(['msg'])
    }
</script>
<style>
</style>