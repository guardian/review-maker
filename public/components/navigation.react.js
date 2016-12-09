import React from 'react';
import { Link } from 'react-router';

export default class NavigationComponent extends React.Component {

    constructor(props) {
        super(props);
    }

    render () {

        return (
            <nav className="navbar navbar-default">
                <div className="navbar-header">
                    <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span className="sr-only">Toggle navigation</span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                    </button>
                    <a className="navbar-brand" href="#">Review Maker</a>
                </div>
                <div id="navbar" className="navbar-collapse collapse">
                    <div className="nav navbar-nav">
                        <Link to='/review/create'>
                            <button type="button" className="btn btn-primary navbar-btn">Create Review</button>
                        </Link>
                    </div>
                </div>
            </nav>
        );
    }
}