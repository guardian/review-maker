import Reqwest from 'reqwest';

export default {

    createReview:(form) => {
        return Reqwest({
            url: '/api/review',
            contentType: 'text/json',
            method: 'post',
            data: JSON.stringify(form)
        })
    },

    listReviews:() => {
        return Reqwest({
            url: '/api/review',
            contentType: 'text/json',
            method: 'get'
        })
    }

}