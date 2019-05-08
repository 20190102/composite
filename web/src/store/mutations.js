export default {

    testEmail(state, { msg }) {
        if (msg === '') {
            state.emailFlag = true
        }
        state.emailMsg = msg
    },

    testPwd(state, { msg }) {
        if (msg === '') {
            state.pwdFlag = true
        }
        state.pwdMsg = msg
    },

    initMsg(state) {
        state.emailMsg = '',
        state.pwdMsg = '',
        state.emailFlag = false,
        state.pwdFlag = false
    },
    msg(state, { msg }) {
        state.msg = msg
    }


}