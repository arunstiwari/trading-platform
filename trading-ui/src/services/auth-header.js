import {UserPool} from "./UserPool";


export const accessTokenHeader = () => {
    let credentials = UserPool.clientId+":"+UserPool.secretHash
    // let credentials = process.env.CLIENT_ID+":"+process.env.CLIENT_SECRET_HASH;
    console.log('--credentials : ', credentials)
    let encryptedCredentials = btoa(credentials);
    console.log('encryptedCredentials : ',encryptedCredentials);
    return { Authorization: 'Basic '+encryptedCredentials};
}

export const accessHeader = (token) => {
    return { Authorization: 'Bearer '+token};
}