import React, {useContext} from 'react';
import {AuthContext} from "../context/AuthContext";

function DAdminRoute()
{
    const {currentUser} = useContext(AuthContext);

    return currentUser.Account_Type == "D";
}

export default DAdminRoute;
