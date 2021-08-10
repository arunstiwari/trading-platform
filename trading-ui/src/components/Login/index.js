import React, {useState, useReducer} from 'react';
import UserService from '../../services/loginservice';
import reducer from "./reducer";

const loginState = {
    loading: false,
    error: undefined
}

const LoginPage = (props) => {
    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const [state, dispatch] = useReducer(reducer,loginState);
    const {loading, error} = state;

    const loginHandler = async (e) => {
        e.preventDefault();
        console.log(' email: ' + email);
        console.log(' password: ' + password);
        dispatch({type: 'LOGIN_INITIATED'});

        const{data,error} =  await UserService.authenticate(email,password);

        if (error){
            dispatch({type: 'LOGIN_UNSUCCESSFULL', payload: error});
        }
        if (data){
            props.history.push("/");
            // window.location.reload();
        }
    }
    if (loading){
        return <div>Loading...</div>
    }
    if (error){
        return <div>Error during login {error}</div>
    }

    return (
        <div>
            <form onSubmit={loginHandler}>
                <input type="email"
                       value={email}
                       placeholder="Enter Email..."
                       onInput={(e) => setEmail(e.target.value)} />
                <input type="password"
                       value={password}
                       placeholder="Enter Password..."
                       onInput={(e) => setPassword(e.target.value)} />
                <button>Login</button>
            </form>
        </div>
    );
};

export default LoginPage;