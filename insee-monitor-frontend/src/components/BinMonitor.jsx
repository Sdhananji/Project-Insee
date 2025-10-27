import React from "react";
import {useState} from "react";
import API from "../api/api";


export default function BinMonitor(){
    const [binNumber, setBinNumber]=useState("");
    const [binData,setBinData] = useState(null);
    const [error,setError]=useState("");

    const handleFetchBin = async () =>{
    console.log("Token: ",localStorage.getItem("token"));
    console.log("Fetching bin:",binNumber);

    //clear previous data and error at the start
    setBinData(null);
    setError("");
    try{
        const token=localStorage.getItem("token");
        const response = await fetch(`http://localhost:8080/api/bins/${binNumber}`, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        });
        
        console.log("Response status:", response.status);
        
        
        const text = await response.text();
        console.log("Raw text:", text);
        
        const data = JSON.parse(text);
        console.log("Parsed data:", data);

        setBinData(data);
        setError("");
    }catch (err){
        console.error("Error fetching bin: ",err);
        setError("Bin not found or unauthorized");
    }
};



    return(
        <div>
            <h2>Bin Monitor</h2>
            <input
                type = "text"
                placeholder="Enter the bin number"
                value = {binNumber}
                onChange={(e) => setBinNumber(e.target.value)}
                />
                <button onClick = {handleFetchBin}>Check Weight </button>

                {error && <p style = {{color:"red"}}>{error}</p>}
                {binData && (
                    <div>
                        <p>Bin Number : {binData.binNumber}</p>
                        <p>Location : {binData.binLocation}</p>
                        <p>Current Weight : {binData.currentWeight}</p>
                        <p>Status : {binData.status}</p>
                    </div>
                )}
        </div>
    );

}