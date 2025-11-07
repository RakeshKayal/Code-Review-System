import axios from "axios";

export const  sendCode = async (code)=>{
    return await axios.post("http://localhost:8080/api/v1.0/code",code);
}