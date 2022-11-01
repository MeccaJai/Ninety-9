import React, {Component} from 'react';
import '../App.css';
import Header from "../components/Header";
import DrinkCard from '../components/UserCard';
import {initializeApp} from "firebase/app";
import {collection, getFirestore, orderBy, query, where} from "firebase/firestore";

class UserSearch extends Component {
    constructor(props){
        super(props);
        this.search = React.createRef();
        this._onButtonClick = this._onButtonClick.bind(this);
        this.state = {users: [] };
    }

    _onButtonClick(evt)
    {
        evt.preventDefault();
        let query_ = this.search.current.value;

        const firebaseConfig = {
            apiKey: "AIzaSyD0O_zMoKyV3Bwrc_QZEftuy14VWyGMadA",
            authDomain: "bar4bar-62819.firebaseapp.com",
            projectId: "bar4bar-62819",
            storageBucket: "bar4bar-62819.appspot.com",
            messagingSenderId: "108693622175",
            appId: "1:108693622175:web:2d2464fa1239e0829281aa",
            measurementId: "G-0RX853S9GS"
        };

        const app = initializeApp(firebaseConfig);
        const db = getFirestore(app);

        const getAllUsers = async () => {
            const accountRef = collection(db, 'Accounts');
            const queryRef = query(accountRef, where("Account_Type", "==", "U"), orderBy("username"));
            queryRef.onSnapshot((snapshot) => {
                let list = [];
                snapshot.docs.forEach((doc) => {
                    let userN = doc.data()['username'];
                    if (userN.includes(query_)){
                        list.push(doc.data())
                    }
                })
                this.setState({users: list})
            })
        }
        getAllUsers();

    }

    render() {
        return (
            <>
                <div className = "container">
                    <Header />
                    <br/>
                    <div className="row row-cols-sm-2 row-cols-md-3 row-cols-xxl-6">
                        <div className = "container" style = {{marginLeft: 150}}>
                            <form id="form" role="search" onSubmit = {this._onButtonClick}>
                                <input className = "input" type="search" id="query" name="q"
                                       placeholder="Hit Enter To Search"
                                       aria-label="Search for a user" ref = {this.search}/>
                            </form>
                        </div>
                        <br/>
                    </div>
                    <br/>
                    <div className="searchCard" style = {{marginLeft: 150}}>
                        {
                            this.state.users && this.state.users.length > 0 ?
                                this.state.users.map((user) => {
                                    return(
                                            <UserCard />
                                        //user card should have the username, email, 
                                        // fname, lname, and isActive
                                    )
                                })
                                : " "
                        }
                    </div>
                </div>
            </>
        );
    }
}

export default UserSearch;
