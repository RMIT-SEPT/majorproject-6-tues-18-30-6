//Note: this is to install the datepicker package- npm install react-datepicker --save 


import React, { Component } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";


export default class ViewAvailable extends Component{
    constructor(props) {
        super(props);
        this.state = {
          selectedDate : "",
        }
      }
    


  mySubmitHandler(e) {

    if (document.getElementById("Datee").value === "") {

      alert("A date must be selected");
      e.preventDefault();
    }


    if (document.getElementById("Timee").value === "") {

      alert("A time must be selected");
      e.preventDefault();
    }


  };

  setSelectedDate(date){
    this.setState({
      selectedDate : date
    });
  }

  render(){
  return(
    <form method="get" onSubmit={this.mySubmitHandler}>


        <h2> Select a date, time combination which is avaliable:</h2>
        <label>
          Please pick a date where you're avaliable:
          <br></br>


          <DatePicker id="Datee" name="date"
          placeholderText="Click to select a date"
        selected={this.state.selectedDate}
        onChange={date => this.setSelectedDate(date)}
        dateFormat='dd/MM/yyyy'

        minDate={new Date()}
      />

        </label>
        <br></br>
        <label>
        Please select an avaliable time:
        <select name="time" id="Timee">
        <option value="">Select</option>
            <option value="9:30 AM">9:30 AM</option>
            <option value="10:30 AM">10:30 AM</option>
            <option value="11:30 AM">11:30 AM</option>
            <option value="12:30 PM">12:30 PM</option>
          </select>
          </label>
          <br></br>
          <label>
          Please select avaliable worker:
          <select name="worker" id="workk">
        <option value="">Select</option>
            <option value="Wanda">Wanda</option>
            <option value="Fred">Fred</option>
            <option value="Brad">Brad</option>
            <option value="Steven">Steven</option>
          </select>

          </label>
        <br></br>
        <input type="submit" value="Confirm Booking" />
      </form>

  )

  }
  }