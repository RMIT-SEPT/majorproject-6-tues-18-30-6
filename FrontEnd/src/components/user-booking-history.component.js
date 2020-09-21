import React from 'react';
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";

export default class BookingHistory extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      bookingHistory: [],
      content: ""
    };
  }

  componentDidMount() {
    console.log("Getting Booking History")
    UserService.getBookingHistory(AuthService.getCurrentUser()).then(
      response => {
        this.setState({
          bookingHistory: response.data
        });
        console.log(response)
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
    let display = true;
    if (this.state.bookingHistory.length === 0) {
      display = false;
    }

    return (
      <div>
        <h2>Booking History</h2>
        {
          display ? (
            this.state.bookingHistory.map((booking) => (
              <li key={booking.id}>Date: {booking.date}, Business: {booking.business}, Worker: {booking.worker}</li>
            ))) : ("No Bookings Found")
        }
      </div>
    );
  }
}