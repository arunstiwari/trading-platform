import React from 'react';
import {Link} from "react-router-dom";

const NavBar = (props) => {
    return (
        <nav className="nav">
            <Link to="/">Trading App</Link>
            <Link to="/login">Login</Link>
            <Link to="/signup">Register</Link>
        </nav>
    );
};

export default NavBar;