//Note: this is to install the datepicker package- npm install react-datepicker --save 

import React, { useState } from "react";
import ReactDOM from "react-dom";
import './style.css';

import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";


  function App () {
  const [selectedDate, setSelectedDate] = useState(null)

  
  function mySubmitHandler(e) {
   
    if (document.getElementById("Datee").value === "") {
      
      alert("A date must be selected");
      e.preventDefault();
    }

    if (document.getElementById("Timee").value === "") {
      
      alert("A time must be selected");
      e.preventDefault();
    }
    
 
  };
  
  
  return(
    <form method="get" onSubmit={mySubmitHandler}>
        
        
        <h2> Select a date, time combination which is avaliable:</h2>
        <label>
          Please pick a date where you're avaliable:
          <br></br>
         

          <DatePicker id="Datee" name="date"
          placeholderText="Click to select a date"
        selected={selectedDate}
        onChange={date => setSelectedDate(date)}
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
        <input type="submit" value="Confirm Booking" />
      </form>
    
  )
  

  }

  
 

 


ReactDOM.render(<App />, document.querySelector("#root"));
