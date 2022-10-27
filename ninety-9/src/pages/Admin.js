import React, {Component} from 'react';
import axios from 'axios';
import UserCard from '../components/UserCard'
import Header from "../components/Header";
import '../App.css';

class Admin extends Component {
constructor(props){
	super(props);
	this.state = {drinks: []}
	this.apiKey = process.env["REACT_APP_DRINK_API_KEY"];
}

render() {
        return (
        <>
            <Header />
            <br/>
            <div className="row row-cols-md-1 row-cols-xxl-6">
                <UserCard />
            </div>
        </>
        );
    }
}
export default Admin;