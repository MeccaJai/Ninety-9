import React, {Component} from 'react';
import axios from 'axios';
import DrinkCard from '../components/DrinkCard';
import Header from "../components/Header";
import '../App.css';

class DrinkList extends Component {
constructor(props){
	super(props);
	this.state = {drinks: []}
	this.apiKey = process.env["REACT_APP_DRINK_API_KEY"];
}

componentDidMount() {
let url = "https://www.thecocktaildb.com/api/json/v2/" + this.apiKey + "/popular.php";

const getDrinks = async () => {
	await axios.get(url)
	    .then(response =>{
	        //set state variable
	          console.log(response);
			  this.setState({drinks: response.data.drinks})
		}).catch((err) =>{
			console.log("Fetch Error: " + err)
		});
    }
    getDrinks();
}
render() {
        return (
        <>
            <Header />
            <h2 className = "text-center shrik" style = {{color: 'Black'}}>Popular Drinks</h2>
            <br/>
            <div className="row row-cols-sm-2 row-cols-md-3 row-cols-xxl-6">
				{
                	this.state.drinks.map((drink) => {
                		return(
                           <DrinkCard drink={drink} key={drink.idDrink} />
                        )
                	})
                }
            </div>
        </>
        );
    }
}
export default DrinkList;