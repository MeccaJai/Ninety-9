import React, {useContext} from 'react';
import {AuthContext} from "../context/AuthContext";
import {Navigate} from "react-router-dom";

function AdminRoute({children})
{
    // const {currentUser} = useContext(AuthContext);

    let localStorageItem = localStorage.getItem("user");

    return (JSON.parse(localStorageItem).Account_Type === "A") ? children : <Navigate to={"/login"} />;
}
export default AdminRoute;
