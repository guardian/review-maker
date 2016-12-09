import React from 'react';
import { Route, IndexRoute } from 'react-router';
import ReactApp from '../components/reactApp.react';
import Home from '../components/home.react';
import ReviewCreate from '../components/review/create.react';
import ReviewEdit from '../components/review/edit.react';

export default [
    <Route path="/" component={ReactApp}>
        <Route name="createReview" path="/review/create" component={ReviewCreate} />
        <Route name="editReview" path="/review/edit/:id" component={ReviewEdit} />
        <IndexRoute component={Home}/>
    </Route>
];