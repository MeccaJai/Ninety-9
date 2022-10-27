import React, {Component} from 'react';
import '../App.css';
import {Link} from 'react-router-dom';

class Home extends Component {
render() {
        return (
            <>
                <div className = "row col-12 text-center">
                    <h1 className = "display-2" style = {{fontFamily: 'Shrikhand', color: 'black'}}>
                       Bar-4-Bar
                    </h1>
                    <h5 style = {{fontStyle: 'italic', color: 'Black'}}>"If you can drink it, We can think it!"</h5>
{/*                     <h5 style = {{color: 'black'}}><Link to = {"/login"} className = "mix shrik">Login</Link> or continue as a guest</h5> */}
                    <hr/>
                </div>
                <div className = "container">
                    <div className = "row">
                        <div className = "col-6">
                            <div className = "text-center mart">
                                <Link to={"/popular"}>
                                    <i className ="btn fa fa-martini-glass-citrus icon" style = {{fontSize: 336, marginLeft: 150, color: 'Black'}}
                                     aria-hidden = "true"></i>
                                </Link>
                                <div className ="container" style = {{marginLeft: 30}}>
                                    <Link to={"/popular"} className = "ht">
                                        <h1 style = {{fontFamily: 'Shrikhand'}}><b>POPULAR&nbsp;DRINKS</b></h1>
                                    </Link>
                                </div>
                            </div>
                        </div>
                        <div className = "col-6">
                            <div className = "row">
                                <div className ="text-center sear">
                                    <Link to={"/search"}>
                                        <i className ="btn fa fa-search fa-5x icon" style = {{fontSize: 120, color: 'Black'}} aria-hidden = "true"></i>
                                    </Link>
                                    <div className ="container">
                                        <Link to={"/search"} className = "ht">
                                            <h2 style = {{fontFamily: 'Shrikhand'}}><b>SEARCH</b></h2>
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <div className = "row">
                                <div className ="text-center ban">
                                    <span className = "fa-stack btn text-center icon" style = {{fontSize: 75}}>
                                        <Link to={"/non"}>
                                            <i className ="fa fa-bottle-droplet fa-stack-1x" data-fa-transform="rotate--30"
                                             aria-hidden = "true" style = {{color: 'Black'}}></i>
                                            <i className ="fa fa-ban fa-stack-2x" style={{color: 'rgba(246, 1, 1, 0.76)'}}></i>
                                        </Link>
                                    </span>
                                    <br/>
                                    <div className ="container">
                                        <br/>
                                        <Link to={"/non"} className = "ht">
                                            <h2 style = {{fontFamily: 'Shrikhand'}}><b>NON-ALCOHOLIC</b></h2>
                                        </Link>
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

export default Home;