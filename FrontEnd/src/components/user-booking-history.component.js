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
        return (
          <div>
          <h2>Booking History</h2>
          {  this.state.bookingHistory.map((booking) => (
              <li key={booking.id}>Date: {booking.date}, # of Passengers: {booking.numPassengers}</li>
           ))
        
          }
          </div>
        );
    }
}