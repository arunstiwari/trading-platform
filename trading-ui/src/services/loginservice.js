import axios from "axios";
import jwtDecode from "jwt-decode";

class UserService {

    authenticate = async (username, password) => {
        try{
            const response = await axios.post(process.env.REACT_APP_AUTHENTICATION_API_URL+"/login", {
                username,
                password
            })
            console.log('response', response);
            const {data,error} = response;
            console.log('data :', data);
            console.log(' error :',error );
            if (data && data.tokens){
                localStorage.setItem("user", JSON.stringify(data.tokens));
                localStorage.setItem("idToken", JSON.stringify(data.tokens.idToken));
                localStorage.setItem("accessToken", JSON.stringify(data.tokens.accessToken));
                localStorage.setItem("userInfo",JSON.stringify(jwtDecode(data.tokens.idToken)));
                console.log("parsing idToken: ",jwtDecode(data.tokens.idToken));
                console.log("parsing accessToken: ",jwtDecode(data.tokens.accessToken));
            }
            return {data,error}
        }catch (e) {
            console.log('---error : ---',e);
        }
        return {data: undefined,error:"Bad Login Credential"}
    }

    signup = async (username, password,group ,profileImage) => {
      const {data,error} =  await axios.post(process.env.REACT_APP_AUTHENTICATION_API_URL+"/signup", {
            username,
            password,
            group: group || 'User',
            profileImage
        })
        console.log('data :', data);
        console.log(' error :',error );
        return {data,error}
    }

    confirmsignup = async (username, confirmationCode) => {
        const {data,error} =  await axios.post(process.env.REACT_APP_AUTHENTICATION_API_URL+"/confirmsignup", {
            username,
            confirmationCode
        })
        console.log('data :', data);
        console.log(' error :',error );
        return {data,error}
    }

}

export default new UserService();