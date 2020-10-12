import React, { Component } from "react";

import UserService from "../services/user.service";
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import AdminManageTimes from "./AdminManageTimes.component";

export default class BoardAdmin extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getAdminBoard().then(
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
        <Tabs>
        <TabList>
          <Tab>Manage Bookings</Tab>
          <Tab>Manage Available Times</Tab>
        </TabList>
    
        <TabPanel>
        <div>
          Functionality not added
        </div>
        </TabPanel>
        <TabPanel>
          <AdminManageTimes />
        </TabPanel>
      </Tabs>
      </div>
    );
  }
}
