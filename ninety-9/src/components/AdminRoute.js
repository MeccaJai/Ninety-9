import React, {useContext} from 'react';
import {AuthContext} from "../context/AuthContext";

function AdminRoute()
{
    const {currentUser} = useContext(AuthContext);

    return currentUser.Account_Type == "A";

}

export default AdminRoute;
