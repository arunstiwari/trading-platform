
let clientId = process.env.REACT_APP_CLIENT_ID;
let secretHash = process.env.REACT_APP_CLIENT_SECRET_HASH;
console.log('clientId : ',clientId);

export const UserPool = {
    clientId: clientId,
    secretHash: secretHash
}