import React, {Component} from 'react';
import '../App.css';
import {Link} from 'react-router-dom';
import axios from "axios";
import { render } from 'react-dom';
import DrinkCard from '../components/DrinkCard';
import {withParams} from '../util/Utilities';
import Header from '../components/Header';

class Recipes extends Component {
    constructor(props) {
        super(props);
        this.drink = this.props.params.drinkID;
        this.apiKey = process.env["REACT_APP_DRINK_API_KEY"];
        console.log(props);
        this.state = {drink: {}};
    }

    componentDidMount() {
        const DRINK_URL = "https://www.thecocktaildb.com/api/json/v2/" + this.apiKey + "/lookup.php?i=" + this.drink;
        const getDrink = async () => {
            await axios.get(DRINK_URL)
                .then(response => {
                    console.log(response)
                    this.setState({drink: response.data.drinks[0]});
                }).catch(err => console.log("Fetch Error: " + err));

        }
        getDrink();
    }

render() {
        const link1 = "/popular";
        const link2 = "/non";
        const link3 = "/search";


        let val = "strIngredient";
        let val2 = "strMeasure";
        let displayList = [];

        for(let i = 1; i <=15; ++i)
            if(this.state.drink[val + i] !== null && this.state.drink[val + i] != "")
            {
                let str = "";

                if(this.state.drink[val2 + i] !== null && this.state.drink[val2 + i] != "")
                    str += this.state.drink[val2 + i] + " ";

                str += this.state.drink[val + i];
                displayList.push(str);
            }
        return (
            <>
                <br/>
                <Header />
                <h2 className = "text-center" style = {{marginRight: 15}}>
                    <Link to = {link1} className = "mix shrik">Popular &nbsp;</Link>
                    <Link to = {link2} className = "mix shrik">Non-Alcoholic &nbsp;</Link>
                    <Link to = {link3} className = "mix shrik">Search</Link>
                </h2>
                <div className = "container">
                    <div className="col mb-3" >
                        <br/>
                        <div className="card h-100 position-relative">
                            <div className="row g-0">
                                <div className = "col-md-4">
                                    <img src={this.state.drink["strDrinkThumb"]} className="recipe-img mx-auto" style = {{borderRadius: 0.25}} alt={this.state.drink["strDrink"]}/>
                                </div>
                                <div className="col-md-8">
                                    <div className="card-body book-card-details">
                                            <h3 className="card-title" style = {{fontFamily: 'Shrikhand'}}>
                                                {this.state.drink.strDrink}
                                                <span className="badge position-absolute top-0 start-0 rounded-pill translate-middle bg-color">
                                                    {this.state.drink.strAlcoholic}
                                                </span>
                                            </h3>
                                        <h4 className="card-subtitle mb-2 book-author">{this.state.drink.strCategory}</h4>
                                        <br/>
                                        {
                                            displayList.map((measures) => {
                                                return(
                                                   <p>{measures}</p>
                                                )
                                            })
                                        }
                                        <br/>
                                        <p>{this.state.drink.strInstructions}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

const Recipe = withParams(Recipes);
export default Recipe;