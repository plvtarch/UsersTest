import React, { Component } from 'react';
import './App.css';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';
import userList from './userList';
import userEdit from "./userEdit";


class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path="/index.html" component={userList} />
            <Route path='/' exact={true} component={userList}/>
            <Route path='/api/users/:id' component={userEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;