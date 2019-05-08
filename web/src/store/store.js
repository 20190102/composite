import Vue from 'vue/dist/vue.js'
import Vuex from 'vuex/dist/vuex.js'
Vue.use(Vuex)

import state from './state.js'
import actions from './actions.js'
import mutations from './mutations.js'

export default new Vuex.Store({
  state,
  actions,
  mutations

})
