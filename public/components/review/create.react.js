import React from 'react';
import ReviewService from '../../services/reviewService';

export default class ReviewCreate extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            reviewer: undefined,
            rating: {
                minRating: undefined,
                maxRating: undefined,
                actualRating: undefined
            },
            reviewType: '',
            entityId: 'no-entity-id',
            reviewSnippet: undefined

        }
    }

    onReviewerChange(e) {
        console.log(e.target.value)
        this.setState({reviewer: e.target.value})
    }

    onMinRatingChange(e) {
        console.log(e.target.value)
        this.setState({minRating: e.target.value})
    }

    onMaxRatingChange(e) {
        console.log(e.target.value)
        this.setState({maxRating: e.target.value})
    }

    onActualRatingChange(e) {
        console.log(e.target.value)
        this.setState({actualRating: e.target.value})
    }

    onReviewTypeChange(e) {
        console.log(e.target.value)
        this.setState({reviewType: e.target.value})

    }

    onReviewSnippetChange(e) {
        console.log(e.target.value)
        this.setState({reviewSnippet: e.target.value})
    }

    onSubmit(e) {
        e.preventDefault()
        const reviewer = this.state.reviewer.trim()
        const minRating = this.state.minRating.trim()
        const maxRating = this.state.maxRating
        const actualRating = this.state.actualRating
        const reviewType = this.state.reviewType
        const reviewSnippet = this.state.reviewSnippet
        const entityId = this.state.entityId

        if (reviewer && minRating && maxRating && actualRating && reviewType && reviewSnippet && entityId) {

            const form = {
                reviewer: reviewer,
                rating: {
                    minRating: minRating,
                    maxRating: maxRating,
                    actualRating: actualRating
                },
                reviewType: reviewType,
                entityId: entityId,
                reviewSnippet: reviewSnippet
            }

            ReviewService.createReview(form).then( response => {
                // TODO: reset form fields
                // TODO: handle alerting
            })

        } else {
            // TODO: Handle alerting
            console.log("error: something went wrong. (provide proper alerting)")
        }


    }

    render() {

        return (
            <div id="page-wrapper">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-3">
                            <form>
                                <div className="form-group">
                                    <select value={this.state.reviewType} onChange={this.onReviewTypeChange.bind(this)} className="form-control">
                                        <option value="" disabled>Please select a review type: </option>
                                        <option value ="Game">Game</option>
                                        <option value="Film">Film</option>
                                        <option value="Restaurant">Restaurant</option>
                                    </select>
                                </div>
                                <div className="form-group">
                                    <label for="reviewer">Email address</label>
                                    <input type="text" onChange={this.onReviewerChange.bind(this)} className="form-control" id="reviewer" placeholder="Reviewer"/>
                                </div>
                                <div className="form-group row">
                                    <div className="col-md-4">
                                        <label for="minimumRating">Min Rating</label>
                                        <input type="text" onChange={this.onMinRatingChange.bind(this)} className="form-control input-inline" id="minimumRating" placeholder="Minimum"/>
                                    </div>
                                    <div className="col-md-4">
                                        <label for="actualRating">Actual Rating</label>
                                        <input type="text" onChange={this.onActualRatingChange.bind(this)} className="form-control input-inline" id="actualRating" placeholder="Actual"/>
                                    </div>
                                    <div className="col-md-4">
                                        <label for="maximumRating">Max Rating</label>
                                        <input type="text" onChange={this.onMaxRatingChange.bind(this)} className="form-control input-inline" id="maximumRating" placeholder="Maximum"/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <label for="reviewSnippet">Review Snippet</label>
                                    <textarea onChange={this.onReviewSnippetChange.bind(this)} className="form-control" rows="4"/>
                                </div>
                                <button type="submit" onClick={this.onSubmit.bind(this)} className="btn btn-success">Submit</button>
                            </form>
                        </div>
                        <div className="col-md-9">

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}