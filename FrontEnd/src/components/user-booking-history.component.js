import React from 'react';
import ReactDOM from 'react-dom';

class BookingHistory extends React.Component {
    componentDidMount() {
        UserService.getBookingHistory().then(
          response => {
            this.setState({
              bookingHistory: response.data
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
          <div>
          <h2>Booking History</h2>
            {this.state.bookingHistory.map(booking) => (
              <li key={booking.id}>Date: {booking.date}, # of Passengers: {booking.numPassengers}</li>
            ))
          }
          </div>
        );
    }
}