import {useState, useContext} from "react";
import API from "../api/api";
import {AuthContext} from "../context/AuthContext";
import {useNavigate} from "react-router-dom";

export default function LoginForm(){
    const [email, setEmail] = useState("");
    const[password, setPassword] = useState("");
    const[error, setError] = useState("");
    const navigate = useNavigate();
    const {login} = useContext(AuthContext);

    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            //send credentials to backend
            const res = await API.post("/auth/login",{email,password});

            //assume backend sends {token "....", worker: {...}}
            const {token, worker} = res.data;

            //save to AuthContext + localStorage
            login(token,worker);

            alert("Login Sucessfull!");
            navigate("/bins");
        }catch(err){
            console.error(err);
            setError("Invalid email or password");
        }

        
    };

    return(
        <div className = "login-container" style ={styles.container}>
            <form onSubmit={handleSubmit} style = {styles.form}>
                <h2>Worker Login</h2>
                {error && <p style = {styles.error}>{error}</p>}

                <input
                    type="email"
                    placeholder = "Enter the email"
                    value = {email}
                    onChange= {(e) => setEmail(e.target.value)}
                required
            style = {styles.input}
        />

        <input
            type = "password"
            placeholder="Enter the password"
            value = {password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={styles.input}
        />

        <button type = "submit" style={styles.button}>
            Login
        </button>
            </form>
        </div>
    )
}


//inline styles for simplicity

const styles={
    container: {
        display: "flex",
        jusifyContent:"center",
        alignItems:"center",
        height:"100vh",
        backgroundColor: "#f5f5f5",

    },
    form:{
        background:"white",
        padding:"30px",
        borderRadius:"12px",
        boxShadow:"0 4px 10px rgba(0,0,0,0.1)",
        display:"flex",
        flexDirection:"column",
        gap:"15px",
        width:"300px",
    },

    input:{
        padding:"10px",
        borderRadius:"8px",
        border:"1px solid #ccc",
        outline:"none",
        fontSize:"14px",
    },

    button:{
        padding:"10px",
        borderRadius:"8px",
        backgroundColor:"#e63946",
        color:"white",
        border:"none",
        cursor:"pointer",
    },

    error:{
        color:"red",
        fontSize:"14px"
    },
};