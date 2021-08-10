const reducer = (state, action) => {
    switch (action.type) {
        case 'SIGNUP_INITIATED':
            return {
                ...state,
                loading: true,
                error: undefined
            }
        case 'SIGNUP_UNSUCCESSFULL':
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