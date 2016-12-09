import React from 'react'
import ReviewService from '../services/ReviewService'
import { Link } from 'react-router'

export default class Home extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            reviews: []
        }
    }

    componentDidMount() {
        ReviewService.listReviews().then(response =>
            this.setState({
                reviews: response
            })
        );
    }

    render() {


        const reviewTableRows = this.state.reviews.map( r =>
            <tr key={r.id}>
                <td>{r.data.review.reviewType}</td>
                <td>{r.data.review.reviewer}</td>
                <td>{r.data.review.rating.actualRating}/{r.data.review.rating.maxRating}</td>
                <td>
                    <Link to={'/review/edit/'+ r.id}>
                        <button type="button" className="btn btn-sm btn-primary navbar-btn">Edit</button>
                    </Link>
                </td>
            </tr>
        )

        return (

            <div id="page-wrapper">
                <div className="container-fluid">

                    <table className="table table-striped">
                        <thead>
                        <tr>
                            <th>Review Type</th>
                            <th>Reviewer</th>
                            <th>Rating</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                            {reviewTableRows}
                        </tbody>
                    </table>

                </div>
            </div>
        );
    }
}