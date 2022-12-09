import React, {useContext, useEffect, useRef, useState} from 'react';
import {AuthContext} from "../context/AuthContext";
import {useNavigate} from "react-router-dom";

function SignUp(props) {
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

        await context.signUp(emailRef.current.value, passwordRef.current.value);

        if(context.currentUser != null)
        {
            context.setErrors(null, false);
            navigate("/");

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

                           {/* this is for the cocktail photo*/}
                           <div className="row">
                               <div className="col-md-7 marFix">
                                   <img src={require('./Cocktail-Making-Techniques.jpg')} alt="Bartender Mixing Drinks" className="img-fluid mb-4"/>
                                   <h1 className="shrik text-center" style = {{fontSize: 45}}>LEARN FROM THE BEST!</h1>
                               </div>

                               <div className="col-md-4 offset-md-1">
                                   <div className="card-body bg-light">
                                       <div className="tab-content">
                                           <div className="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">

                                               {/* error message for wrong login */}
                                               {
                                                   errors && errors.length > 0 ?
                                                       <div className="alert alert-danger">{errors[0].err.message}</div>
                                                       :""
                                               }

                                               {/* form for the login */}
                                               <form onSubmit={handleSubmit}>

                                                   <div className="form-group mb-3">
                                                       <label className="form-label shrik" for="loginName"> Username: </label>
                                                       <input type="email" id="loginName" className="form-control formFix" required ref={emailRef}/>
                                                   </div>

                                                   {/* Password Form*/}
                                                       <div className="form-outline mb-4">
                                                           <label className="form-label shrik" for="loginPassword">Password:</label>
                                                           <input type="password" id="loginPassword" className="form-control formFix" required ref={passwordRef}/>
                                                       </div>
                                                       <button className="w-50 btn btn-outline-light" type="submit" id= "oval" style={{fontSize: 14}} className="btn btn-default shrik mb-4" >Register</button>


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

   export default SignUp;