import React from 'react';
import Navigation from './navigation.react';

export default class ReactApp extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div id="wrapper">
                <Navigation />
                {this.props.children}
            </div>
        );
    }
}