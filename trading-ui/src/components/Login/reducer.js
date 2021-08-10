const reducer = (state, action) => {
    switch (action.type) {
        case 'LOGIN_INITIATED':
            return {
                ...state,
                loading: true,
                error: undefined
            }
        case 'LOGIN_UNSUCCESSFULL':
           return {
               ...state,
               loading: false,
               error: action.payload
           }
        default:
            return state;
    }
}

export  default  reducer;