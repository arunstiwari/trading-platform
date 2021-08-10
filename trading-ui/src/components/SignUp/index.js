import React, {useState, useReducer} from 'react';
import UserService from '../../services/loginservice';
import reducer from "./reducer";

const initialState = {
    loading: false,
    error: undefined
}

const SignUpPage = (props) => {
    const [state, dispatch] = useReducer(reducer,initialState);
    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[profile, setProfile] = useState('');

    const {loading, error} = state;

    const signUpHandler = async (e) => {
        e.preventDefault();
        console.log(' email: ' + email);
        console.log(' password: ' + password);
        dispatch({type: 'SIGNUP_INITIATED'});

        const{data,error} =  await UserService.signup(email,password,'User', profile);

        if (error){
            dispatch({type: 'SIGNUP_UNSUCCESSFULL', payload: error});
        }
        if (data){
            props.history.push("/confirmsignup");
            window.location.reload();
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
            <form onSubmit={signUpHandler}>
                <input type="email"
                       value={email}
                       placeholder="Enter Email..."
                       onInput={(e) => setEmail(e.target.value)} />
                <input type="password"
                       value={password}
                       placeholder="Enter Password..."
                       onInput={(e) => setPassword(e.target.value)} />
                <input type="text"
                       value={profile}
                       onInput={e => setProfile(e.target.value)}
                       placeholder="Enter Profile image Path"
                />
                <button>Register</button>
            </form>
        </div>
    );
};

export default SignUpPage;
