import React, {useContext, useEffect, useRef, useState} from 'react';
import {AuthContext} from "../context/AuthContext";
import { useNavigate} from "react-router-dom";
import '../App.css';

function SignIn(props) {
    const context = useContext(AuthContext);
    const emailRef = useRef("");
    const passwordRef = useRef("");
    const [errors, setErrors] = useState([]);
    let navigate = useNavigate();

    useEffect(()=>{
        window.document.body.classList.add("login");

    })

    async function handleSubmit(event){

        event.preventDefault();

        await context.signIn(emailRef.current.value, passwordRef.current.value);

        if(context.currentUser != null)
        {
            context.setErrors(null, false);
            navigate("/home");

        }
        else {
            setErrors(context.errors);
        }
    }
    return (
        <>
            <div className="container full-page">
                <h1 className="text-center mb-4 shrik display-2" style = {{color: 'black'}}>
                    Bar-4-Bar
                </h1>
                <div className="row">
                    <div className="col-md-7 marFix">
                        <img src={require('./Cocktail-Making-Techniques.jpg')} alt="Bartender Mixing Drinks" class="img-fluid mb-4"/>
                        <h1 className="shrik text-center" style = {{fontSize: 45}}>LEARN FROM THE BEST!</h1>
                    </div>

                    <div className="col-md-4 offset-md-1">
                        <div className="card-body bg-light">
                            <div className="tab-content">
                                <div className="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                                    <form>
                                        {/* Username Form */}
                                        <div className="form-outline mb-4">
                                            <label className="form-label shrik" for="loginName">Username:</label>
                                            <input type="email" id="loginName" className="form-control formFix" />
                                        </div>
                                        {/* Password Form*/}
                                        <div className="form-outline mb-4">
                                            <label className="form-label shrik" for="loginPassword">Password:</label>
                                            <input type="password" id="loginPassword" className="form-control formFix" />
                                        </div>
                                        {/* Buttons */}
                                        <div className="mx-auto text-center">
                                            {/* Log In Button */}
                                            <div>
                                                <button type="submit" id= "oval" style={{fontSize: 14}} className="btn btn-default shrik mb-4">Log In</button>
                                            </div>
                                            {/* Create Account Button */}
                                            <div>
                                                <button type="submit" id= "oval" style={{fontSize: 14}} className="btn btn-default mb-4 shrik">Create Account</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default SignIn;