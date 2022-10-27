import React from 'react';
import {Link} from "react-router-dom";
import '../App.css';

function UserCard(props) {
	return (
		<>
		    <div className="offset-md-2 col-md-8 mb-3" >
                <br/>
                <div className="card h-100 position-relative userMar ">
                    <div className="row g-0">
                        <div className="col-md-3">
                            <i class="fa-solid fa-user fa-10x mb-2"></i>
                            <h5 className = "mix shrik userNameSize">
                                MeccaLecca
                            </h5>
                        </div>
                        <div className="col-md-4 offset-md-1">
                            <h5 className = "mix userNameSize shrik">
                                Mecca Thornton
                            </h5>
                            <h5 style = {{fontStyle: 'italic', fontSize: 19}}>
                                mthorn@mail.com
                                Approved
                            </h5>
                        </div>
                    </div>
                </div>
            </div>
		</>
	);
}

export default UserCard;