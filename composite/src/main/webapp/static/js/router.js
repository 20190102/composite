import VueRouter from 'vue-router'

	var login={
        template:"#login"
    }
    var register={
        template:"#register",
		props:['getEmail'],
		methods:{
			getValidatea(){
				this.$emit('getValidate')
			},
			getreg(){
				this.$emit('register')
			}
		}

    }
    var router=new VueRouter({
        routes:[
            {path:'/',redirect:'/login'},
            {path:'/login',component:login},
            {path:'/register',component:register}
        ]
    })

export default VueRouter