import './App.css';
import Home from "./pages/Home";
import DrinkList from "./pages/Drinks";
import NonList from "./pages/Non";
import Recipe from "./pages/Recipe";
import Search from "./pages/Search";
import SignIn from "./pages/SignIn";
import DAdmin from "./pages/DAdmin";
import Approval from "./pages/Approval";
import Admin from "./pages/Admin";
import {BrowserRouter as Router, Route, Routes, Link} from 'react-router-dom';
import {AuthProvider} from "../context/AuthContext";
import PrivateRoute from "./PrivateRoute";

function App(){

    return (
        <>
            <AuthProvider>
                <Router>
                    <div className = "container">
                        <div className="row">
                            <div className="col-12">
                                <Routes>
                                    <Route exact path="/" exact element = {<SignIn />}/>
                                    <Route path="/home" element = {<PrivateRoute><Home /></PrivateRoute>} />
                                    <Route path="/popular" element={<PrivateRoute><DrinkList /></PrivateRoute>}/>
                                    <Route path="/non" element={<PrivateRoute><NonList /></PrivateRoute>}/>
                                    <Route path="/popular/:drinkID" element={<PrivateRoute><Recipe /></PrivateRoute>}/>
                                    <Route path="/search" element={<PrivateRoute><Search /></PrivateRoute>}/>
                                    <Route path="/DAdmin/:drinkID" element={<PrivateRoute><Approval /></PrivateRoute>}/>
                                    <Route path="/DAdmin" element={<PrivateRoute><DAdmin /></PrivateRoute>}/>
                                    <Route path="/Admin" element={<PrivateRoute><Admin /></PrivateRoute>}/>
                                </Routes>
                            </div>
                        </div>
                        <br/>
                        <br/>
                    </div>
                </Router>
            </AuthProvider>
        </>
    );

}

export default App;
