import React from 'react';

export default class ReviewEdit extends React.Component {

    constructor(props) {
        super(props)
    }

    componentDidMount() {
        const reviewId = this.props.params.id
        console.log(reviewId)
    }


    render() {

        return (
            <div id="page-wrapper">
                <div className="container-fluid">
                    Edit Page
                </div>
            </div>
        )
    }
}