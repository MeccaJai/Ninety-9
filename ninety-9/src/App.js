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
                                    <Route path="/home" element = {<Home />} />
                                    <Route path="/popular" element={<DrinkList />}/>
                                    <Route path="/non" element={<NonList />}/>
                                    <Route path="/popular/:drinkID" element={<Recipe />}/>
                                    <Route path="/search" element={<Search />}/>
                                    <Route path="/DAdmin/:drinkID" element={<Approval />}/>
                                    <Route path="/DAdmin" element={<DAdmin />}/>
                                    <Route path="/Admin" element={<Admin />}/>
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
