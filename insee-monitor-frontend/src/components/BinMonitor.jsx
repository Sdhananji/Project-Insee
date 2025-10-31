import React, { useState, useEffect, useCallback } from "react";

export default function BinMonitor() {
  const [binNumber, setBinNumber] = useState("");
  const [binData, setBinData] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [autoRefresh, setAutoRefresh] = useState(false);

  const handleFetchBin = useCallback(async () => {
    console.log("Token:", localStorage.getItem("token"));
    console.log("Fetching bin:", binNumber);

    // Clear previous data and errors
    setBinData(null);
    setError("");

    if (!binNumber) {
      setError("Please enter a bin number");
      return;
    }

    try {
      setLoading(true);
      const token = localStorage.getItem("token");

      const response = await fetch(`http://localhost:8080/api/bins/${binNumber}`, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });

      console.log("Response status:", response.status);

      // ✅ Fix: correct logic here
      if (!response.ok) {
        setError("Bin not found or unauthorized");
        setBinData(null);
        setAutoRefresh(false);
        return;
      }

      const text = await response.text();
      console.log("Raw text:", text);

      if (!text) {
        throw new Error("Empty Response from server");
      }

      let data = {};
      try {
        data = JSON.parse(text);
      } catch (parseErr) {
        console.error("Error parsing JSON:", parseErr);
        setError("Error parsing server response");
        setAutoRefresh(false);
        return;
      }

      console.log("Parsed data:", data);
      setBinData(data);
      setError("");
      setAutoRefresh(true);
    } catch (err) {
      console.error("Error fetching bin:", err);
      setError("Bin not found or unauthorized");
      setBinData(null);
      setAutoRefresh(false);
    } finally {
      setLoading(false);
    }
  }, [binNumber]); // ✅ dependencies — only re-creates when binNumber changes

  // Auto-refresh every 5 seconds if a bin number is entered
  useEffect(() => {
    if (autoRefresh && binNumber) {
      const interval = setInterval(() => handleFetchBin(), 5000);
      return () => clearInterval(interval);
    }
  }, [autoRefresh, binNumber, handleFetchBin]);

  const handleChange = (e) => {
    setBinNumber(e.target.value.trim().toUpperCase());
  };

  return (
    <div style={{ textAlign: "center", marginTop: "40px" }}>
      <h2>Bin Monitor</h2>
      <input
        type="text"
        placeholder="Enter the bin number"
        value={binNumber}
        onChange={handleChange}
        style={{
          padding: "8px",
          width: "200px",
          borderRadius: "5px",
          border: "1px solid #ccc",
          marginRight: "10px",
        }}
      />
      <button
        onClick={handleFetchBin}
        disabled={loading}
        style={{
          padding: "8px 16px",
          borderRadius: "5px",
          border: "none",
          backgroundColor: "#007bff",
          color: "white",
          cursor: "pointer",
        }}
      >
        {loading ? "Loading..." : "Check Weight"}
      </button>

      {error && <p style={{ color: "red", marginTop: "20px" }}>{error}</p>}
      {binData && (
        <div
          style={{
            marginTop: "30px",
            border: "1px solid #ddd",
            borderRadius: "10px",
            padding: "20px",
            width: "300px",
            margin: "20px auto",
            boxShadow: "0px 4px 8px rgba(0,0,0,0.1)",
          }}
        >
          <p>
            <strong>Bin Number: </strong>
            {binData.binNumber}
          </p>
          <p>
            <strong>Location: </strong>
            {binData.binLocation}
          </p>
          <p>
            <strong>Current Weight: </strong>
            {binData.currentWeight}
          </p>
          <p>
            <strong>Status: </strong>
            {binData.status}
          </p>
        </div>
      )}
    </div>
  );
}
