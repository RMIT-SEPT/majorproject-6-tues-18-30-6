import React, { Component } from "react";

import UserService from "../services/user.service";
import 'react-tabs/style/react-tabs.css';

export default class BoardWorker extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getWorkerBoard().then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>Provide Availability</h3>
        </header>
        Functionality not added
      </div>
    );
  }
}
