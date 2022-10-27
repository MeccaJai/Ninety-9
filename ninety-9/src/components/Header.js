import React, {Component} from 'react';
import '../App.css';
import {Link} from 'react-router-dom';

class Header extends Component {
render() {
        return (
            <>
                <div className = "container text-center">
                    <Link to = "/home" className = "headhov">
                        <h1 className = "display-2" style = {{fontFamily: 'Shrikhand'}}>
                           Bar-4-Bar
                        </h1>
                    </Link>
                </div>
            </>
        );
    }
}

export default Header;