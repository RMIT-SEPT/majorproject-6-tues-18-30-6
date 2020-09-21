import React, { Component } from "react";

import UserService from "../services/user.service";
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import BookingHistory from "./user-booking-history.component"
import EditDetails from "./user-edit-details.component";
import ViewAvailable from "./user-ViewAvailable.component";
import SearchPage from "./user-SearchBooking.component";

export default class BoardUser extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getUserBoard().then(
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
        <Tabs>
        <TabList>
          <Tab>Booking History</Tab>
          <Tab>Edit Details</Tab>
          <Tab>View Bookings</Tab>
          <Tab>Search Bookings</Tab>
        </TabList>
    
        <TabPanel>
        <div>
          <BookingHistory />
        </div>
        </TabPanel>
        <TabPanel>
          <EditDetails />
        </TabPanel>
        <TabPanel>
          <ViewAvailable />
        </TabPanel>
        <TabPanel>
          <SearchPage />
        </TabPanel>
      </Tabs>
        </header>
      </div>
    );
  }
}
