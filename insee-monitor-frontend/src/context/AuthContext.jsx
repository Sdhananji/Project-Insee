import {createContext, useState} from "react";

export const AuthContext = createContext();

export const AuthProvider = ({children})=>{
    const [token,setToken] = useState(localStorage.getItem("token"));
    const[user, setUser] = useState(null);


    const login = (token,user) => {
        setToken(token);
        setUser(user);
        localStorage.setItem("token", token);
    };

    const logout = () => {
        setToken(token);
        setUser(user);
        localStorage.removeItem("token");
    }

    return (
        <AuthContext.Provider value = {{token,user,login,logout}}>
            {children}
        </AuthContext.Provider>
    );
};