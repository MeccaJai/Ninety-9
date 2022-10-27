import React from 'react';
import {Link} from "react-router-dom";
import '../App.css';

function TestDrink(props) {

    let drink = props.drink;
    const link = "/DAdmin/" + drink.idDrink;

	return (
		<>
		    <div className="col mb-3" >
		        <Link to = {link} style = {{textDecoration: 'none'}}>
                    <div className="clickCard card h-100 position-relative testBorder">
                        <div className="row g-0">
                            <div className="col-md-8">
                                <div className="card-body book-card-details">
                                        <h3 className="card-title book-title mix shrik">
                                            {drink.strDrink}
                                        </h3>
                                    <h5 className="card-subtitle mb-2 category">{drink.strCategory}</h5>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </Link>
            </div>
		</>
	);
}

export default TestDrink;