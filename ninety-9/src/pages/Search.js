import React, {Component} from 'react';
import axios from 'axios';
import DrinkCard from '../components/DrinkCard';
import Header from "../components/Header";
import '../App.css';

class Search extends Component {
constructor(props){
	super(props);
	this.search = React.createRef();
    this._onButtonClick = this._onButtonClick.bind(this);
    this.state = {drinks: []};
    this.apiKey = process.env["REACT_APP_DRINK_API_KEY"];
}

_onButtonClick(evt)
{
    evt.preventDefault();
    let query = this.search.current.value;
    let DRINK_URL = "https://www.thecocktaildb.com/api/json/v2/" + this.apiKey + "/search.php?s=" + query;

    const getDrink = async () => {
        await axios.get(DRINK_URL)
            .then(response => {
                console.log(response);
                console.log(query);
                this.setState({drinks: response.data.drinks});
            }).catch(err => console.log("Fetch Error: " + err));

    }
    getDrink();

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
                                 aria-label="Search for a drink recipe" ref = {this.search}/>
                            </form>
                        </div>
                        <br/>
                    </div>
                    <br/>
                    <div className="searchCard" style = {{marginLeft: 150}}>
                        {
                            this.state.drinks && this.state.drinks.length > 0 ?
                                this.state.drinks.map((drink) => {
                                    return(
                                        <DrinkCard drink={drink} key={drink.idDrink} />
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

export default Search;

/*
    {this.state.drink != null ?
        <DrinkCard drink = {this.state.drink} key = {this.state.drink.idDrink} />
        : " "
    }
*/