import './App.css';
import {BrowserRouter as Router} from "react-router-dom";
import TradingAppRoutes from "./routers";
import NavBar from "./components/ui/NavBar";

function App() {
  return (
    <Router>
      <NavBar />
      <TradingAppRoutes />
    </Router>
  );
}

export default App;
