import React, {useContext} from 'react';
import {AuthContext} from "../context/AuthContext";
import {Navigate} from "react-router-dom";

function DAdminRoute({children})
{
    // const {currentUser} = useContext(AuthContext);

    let localStorageItem = localStorage.getItem("user");

    return (JSON.parse(localStorageItem).Account_Type === "D") ? children : <Navigate to={"/login"} />;
}
export default DAdminRoute;
