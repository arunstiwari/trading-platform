import React from 'react';
import {Route} from "react-router-dom";
import LandingPage from "../pages/LandingPage";
import LoginPage from "../components/Login";
import SignUpPage from "../components/SignUp";
import ConfirmSignup from "../components/SignUp/ConfirmSignup";
import Stocks from "../components/Stocks";


const TradingAppRoutes = () => {
    return (
        <div>
            <Route exact path="/" component={LandingPage} />
            <Route exact path="/login" component={LoginPage} />
            <Route exact path="/signup" component={SignUpPage} />
            <Route exact path="/confirmsignup" component={ConfirmSignup} />
            <Route exact path="/stocks" component={Stocks} />
        </div>
    );
};

export default TradingAppRoutes;