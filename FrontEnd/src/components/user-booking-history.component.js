import React from 'react';
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";



export default class BookingHistory extends React.Component {
  constructor(props) {
    super(props);
    this.formatTime = this.formatTime.bind(this);

    this.state = {
      bookingHistory: [],
      content: ""
    };
  }

  formatTime(start, end){
    let time = "";
    start = new Date(start);
    end = new Date(end);
    let startTime = start.toTimeString();
    let endTime = end.toTimeString();

    time = start.toDateString() + ": " + startTime.slice(0,5) + "-" + endTime.slice(0,5);
    

    return time;
  }

  componentDidMount() {
    console.log("Getting Booking History")
    UserService.getBookingHistory(AuthService.getCurrentUser()).then(
      response => {
        this.setState({
          bookingHistory: response.data
        });
        console.log(response.data)
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
              <div class = "container">
              <li key={booking.id}>Date: {this.formatTime(booking.time.startTime, booking.time.endTime)}, Worker: {booking.time.worker.username}</li>
              </div>
            ))) : ("No Bookings Found")
        }
      </div>
    );
  }
}