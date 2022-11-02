import React from 'react';
import {Link} from "react-router-dom";
import '../App.css';
import {initializeApp} from "firebase/app";
import {collection, getFirestore, orderBy, query, where, doc, setDoc, updateDoc} from "firebase/firestore";

async function Deactivate(props) {
    const firebaseConfig = {
        apiKey: "AIzaSyD0O_zMoKyV3Bwrc_QZEftuy14VWyGMadA",
        authDomain: "bar4bar-62819.firebaseapp.com",
        projectId: "bar4bar-62819",
        storageBucket: "bar4bar-62819.appspot.com",
        messagingSenderId: "108693622175",
        appId: "1:108693622175:web:2d2464fa1239e0829281aa",
        measurementId: "G-0RX853S9GS"
    };

    const user = props.user;
    const app = initializeApp(firebaseConfig);
    const db = getFirestore(app);

    const accountRef = doc(db, "Accounts", user.id);
    await updateDoc(accountRef, {
        isActive: false
    });

    if(user.isActive == false){
        console.log("Successfully deactivated account...");
    }
}


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