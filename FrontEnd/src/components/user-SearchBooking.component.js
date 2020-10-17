import React from "react";
import BookingService from "../services/booking.service";

export default class SearchPage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      times: [],
      rawTimes: [],
      successful: "",
      message: "",
    };
  }

  componentDidMount() {
    BookingService.getAvailableBookings(1).then(
      (response) => {
        //Formatting the request to fit with the previously existing search algorithm
        var formattedTimes = [];
        for (let i = 0; i < response.data.length; i++) {
          let date = new Date(response.data[i].startTime);
          formattedTimes.push({
            minute: date.toTimeString().slice(3, 5),
            hour: date.toTimeString().slice(0, 2),
            date:
              response.data[i].startTime.slice(0, 4) +
              "-" +
              response.data[i].startTime.slice(5, 7) +
              "-" +
              response.data[i].startTime.slice(8, 10),
            inputMatch: true,
            advanceMatch: true,
            dateSelectMatch: true,
          });
        }
        this.setState({
          times: formattedTimes,
        });
        console.log(this.state.times);
        console.log(response);
      },
      (error) => {
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString(),
        });
      }
    );
  }

  searchInput = () => {
    console.log("searchInput");
    let searchInput = document.getElementById("searchInput").value;
    let copy = this.state.times.slice();

    for (let i = 0; i < copy.length; i++) {
      let time = copy[i].hour + ":" + copy[i].minute;
      if (searchInput === "") {
        copy[i].inputMatch = true;
      } else {
        copy[i].inputMatch = time === searchInput ? true : false;
      }
    }

    console.log(copy);
    this.setState({
      times: copy,
    });
  };

  dateSelect = () => {
    let dateSelector = document.getElementById("dateSelector").value;
    let copy = this.state.times.slice();
    console.log(dateSelector);
    for (let i = 0; i < copy.length; i++) {
      if (dateSelector === "") {
        copy[i].dateSelectMatch = true;
      } else {
        copy[i].dateSelectMatch = copy[i].date === dateSelector ? true : false;
      }
    }
    console.log(copy);
    this.setState({
      times: copy,
    });
  };

  advance = () => {
    console.log("advance");
    let advance = document.getElementById("advance");
    let copy = this.state.times.slice();
    for (let i = 0; i < copy.length; i++) {
      if (advance.options[advance.selectedIndex].text === "morning") {
        copy[i].advanceMatch = parseInt(copy[i].hour) < 12 ? true : false;
      } else if (advance.options[advance.selectedIndex].text === "afternoon") {
        copy[i].advanceMatch = parseInt(copy[i].hour) >= 12 ? true : false;
      } else {
        copy[i].advanceMatch = true;
      }
    }
    console.log(copy);
    this.setState({
      times: copy,
    });
  };

  render() {
    //time lists to be rendered
    let timeLists = this.state.times.map((item, index) => {
      if (item.advanceMatch && item.dateSelectMatch && item.inputMatch) {
        return (
          <div class="row" key={index}>
            <div class="col-10 listItem">
              <span>{item.date}</span>
              &nbsp;
              <span>{item.hour + ":" + item.minute}</span>
            </div>

            <div class="col-2">
              <button type="button" class="btn btn-primary">
                Register
              </button>
            </div>
          </div>
        );
      } else {
        return null;
      }
    });

    return (
      <div class="container">
        <div class="row main">
          <div class="col">
            <div class="input-group mb-3">
              <input
                id="searchInput"
                type="text"
                class="form-control"
                placeholder="Search a Time"
              />
              <div class="input-group-append">
                <button
                  class="btn btn-outline-secondary"
                  type="button"
                  id="button-addon2"
                  onClick={this.searchInput}
                >
                  Search
                </button>
              </div>
            </div>
          </div>
        </div>

        <div class="row main">
          <div class="offset-4 col-4">
            <input onChange={this.dateSelect} type="date" id="dateSelector" />
          </div>
          <div class="col-4">
            <select id="advance" onChange={this.advance}>
              <option defaultValue="advance">advance</option>
              <option value="morning">morning</option>
              <option value="afternoon">afternoon</option>
            </select>
          </div>
        </div>

        <div class="main">{timeLists}</div>
      </div>
    );
  }
}
