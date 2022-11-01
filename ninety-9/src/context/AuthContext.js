import React, {Component} from 'react';
import axios from "axios";
import {initializeApp} from "firebase/app";
import {getAuth, signInWithEmailAndPassword} from "firebase/auth";


export const AuthContext = React.createContext({
    currentUser: {},
    errors: [],
    refresh: null,
    setErrors: () =>{},
    setCurrentUser: () => {},
    signIn: () =>{},
    signOut: () =>{},

})

export class AuthProvider extends Component {

    state = {
        currentUser: {},
        setErrors: (errObject, append) => {
            if(append)
            {
                let e = this.state.errors;
                e.push(errObject);
                this.setState({errors: e});
            }
            else{
                this.setState({errors: [errObject]});
            }
        },
        setCurrentUser: user =>{
            this.setState({currentUser: user})
        },
        signIn: async (email, password)=>{

            const firebaseConfig = {
                apiKey: "AIzaSyD0O_zMoKyV3Bwrc_QZEftuy14VWyGMadA",
                authDomain: "bar4bar-62819.firebaseapp.com",
                projectId: "bar4bar-62819",
                storageBucket: "bar4bar-62819.appspot.com",
                messagingSenderId: "108693622175",
                appId: "1:108693622175:web:2d2464fa1239e0829281aa",
                measurementId: "G-0RX853S9GS"
            };
            // Initialize Firebase
            initializeApp();

            const auth = getAuth();
            signInWithEmailAndPassword(auth, email, password)
                .then(async (cred)=>{
                    let user = cred.user;
                    let res = await user.getIdTokenResult(false);
                    let token = res.token;
                    localStorage.setItem("firebaseResponse", JSON.stringify(res));
                    let headers = {"Authorization": "Bearer " + token}

                    await axios.post ("http://localhost:8080/auth/session", document.body, {
                        headers: headers,
                        context: document.body
                    }).then((res)=>{
                        this.state.setCurrentUser(res.data.customer);
                        localStorage.setItem("user", JSON.stringify(this.state.currentUser));
                    }).catch((err) => {
                        console.log(err);
                        this.state.setErrors(err.response.data, false);
                    })
                })
                .catch(function (err) {
                    // Handle Errors here.
                    this.state.setErrors(err.response.data, false);
                });

            //refersh token every 30 minutes
            this.state.refresh = setInterval(this.getRefresh, 300000, auth)
        },
        signOut: async ()=>{
            await axios.get("http://localhost:3000/logout").then( res =>{
                this.state.setCurrentUser({});
                localStorage.removeItem("user");
                localStorage.removeItem("firebaseResponse");
                //stop token refresh
                clearInterval(this.state.refresh );
            }).catch(err => console.log(err));
        }
    }

    getRefresh(auth){

        auth.currentUser.getIdToken(true).then((response)=>{
            let fbResponse = localStorage.getItem("firebaseResponse")
            fbResponse = (fbResponse ? JSON.parse(fbResponse) : {})
            fbResponse.token = response.id_token;
            localStorage.setItem("firebaseResponse", JSON.stringify(fbResponse))
        }).catch((err) => {
            console.log(err);
        })
    }

    render() {

        const { children } = this.props
        const {currentUser, errors, cart,refresh, setErrors, setCurrentUser, signIn, signOut, addToCart, removeFromCart } = this.state

        return (
            <AuthContext.Provider value={{currentUser, errors, cart, refresh, setErrors, setCurrentUser, signIn, signOut, addToCart, removeFromCart}}>
                {children}
            </AuthContext.Provider>
        );
    }
}

export const AuthConsumer = AuthContext.Consumer;
