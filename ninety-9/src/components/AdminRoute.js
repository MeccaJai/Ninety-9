import React, {useContext} from 'react';
import {AuthContext} from "../context/AuthContext";

function AdminRoute()
{
    // const {currentUser} = useContext(AuthContext);

    let localStorageItem = localStorage.getItem("user");

    return (JSON.parse(localStorageItem).Account_Type === "A");

}

export default AdminRoute;
