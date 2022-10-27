import React from 'react';
import {Link} from "react-router-dom";
import '../App.css';

function DrinkCard(props) {

    let drink = props.drink;
    const link = "/popular/" + drink.idDrink;

	return (
		<>
		    <div className="col mb-3" >
		        <Link to = {link} style = {{textDecoration: 'none'}}>
                    <div className="clickCard card h-100 position-relative">
                        <div className="row g-0">
                            <div className = "col-md-4">
                                <img src={drink.strDrinkThumb} className="drinkCard-img mx-auto" style = {{borderRadius: 0.25}} alt={drink.strDrink}/>
                            </div>
                            <div className="col-md-8">
                                <div className="card-body book-card-details">
                                        <h4 className="card-title book-title">
                                            <Link to = {link} className = "mix shrik">
                                                {drink.strDrink}
                                            </Link>
                                            <span className="badge position-absolute top-0 start-0 rounded-pill translate-middle bg-color shrik"
                                             style = {{font: 10}}>
                                                {drink.strAlcoholic}
                                            </span>
                                        </h4>
                                    <h5 className="card-subtitle mb-2 book-author">{drink.strCategory}</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </Link>
            </div>
		</>
	);
}

export default DrinkCard;