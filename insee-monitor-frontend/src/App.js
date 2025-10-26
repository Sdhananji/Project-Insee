
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {AuthProvider} from "./context/AuthContext";
import LoginForm from "./components/LoginForm";
import BinMonitor from "./components/BinMonitor";




function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path = "/" element= {<LoginForm />} />
          <Route path = "bins" element={<BinMonitor />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
