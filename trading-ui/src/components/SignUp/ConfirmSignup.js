import React, {useState} from 'react';
import UserService from "../../services/loginservice";

const ConfirmSignup = (props) => {
    const[username, setUsername] =useState('');
    const[confirmationCode, setConfirmationCode] =useState('');

    const confirmsignupHandler = async (e) => {
        e.preventDefault();
        console.log('username: ' + username);
        console.log('confirmationCode: ' + confirmationCode);
        const {data, error} = await UserService.confirmsignup(username, confirmationCode);
        console.log('data: ' + data);
        console.log('error: ' + error);
        if (data){
            props.history.push("/login");
        }
    }
    return (
        <form onSubmit={confirmsignupHandler}>
            <input type="email"
                   value={username}
                   placeholder="Enter Email..."
                   onInput={(e) => setUsername(e.target.value)} />
            <input type="text"
                   value={confirmationCode}
                   placeholder="Enter ConfirmationCode..."
                   onInput={(e) => setConfirmationCode(e.target.value)} />
            <button>Confirm Email</button>
        </form>
    );
};

export default ConfirmSignup;