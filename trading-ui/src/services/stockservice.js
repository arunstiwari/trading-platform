import axios from "axios";
import {accessHeader, accessTokenHeader} from "./auth-header";

class Stockservice {
    fetchStocks = async () => {
        try {
            const{data:access, error: accessError}= await this.fetchAccessToken();
            const authorizationHeader = accessHeader(access.access_token);

            const headers = {
                ...authorizationHeader,
                'Access-Control-Allow-Origin': '*'
            }
            console.log(' headers: ', headers);

            const {data,error, status} = await axios.get(process.env.REACT_APP_STOCK_API_URL+"/stocks",
                {headers: headers});
            console.log('data ',data , ' error : ',error, ' status :',status);

            return {data,error};
        }catch (e) {
            console.error('Error fetching stocks ',e);
            return {data:undefined,error:'Error fetching stocks'};
        }

    }

    fetchAccessToken = async () => {
        const{data,error} = await axios.post(process.env.REACT_APP_ACCESS_TOKEN_API_URL,null, {headers: accessTokenHeader()});
        console.log('accessToken: ' + data);
        return {data,error};
    }
}

export  default  new Stockservice();