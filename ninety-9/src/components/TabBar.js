import React from "react";
import {Link} from "react-router-dom";

function TabBar (){
    return(
        <ul className="nav nav-tabs justify-content-center">

        <li className="nav-item">
            <Link to="/home" className="nav-link active" aria-current="page" aria-expanded="false"> Home </Link>
        </li>

        <li className="nav-item">
            <Link to="/search" className="nav-link active" aria-current="page"
                  aria-expanded="false"> Search </Link>
        </li>

        <li className="nav-item">
            <Link to="/" className="nav-link active" aria-current="page"
                 aria-expanded="false"> Sign Out </Link>
        </li>
    </ul>
    )


}

export default TabBar;
