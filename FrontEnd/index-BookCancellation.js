
import React, { useState } from "react";
import ReactDOM from "react-dom";
import './style.css';

import "react-datepicker/dist/react-datepicker.css";


  function App () {
  const [selectedDate, setSelectedDate] = useState(null)

  
  function mySubmitHandler(e) {
   
   
    if (document.getElementById("Timee").value === "") {
      
      alert("Please select a reason for cancelling");
      e.preventDefault();
    }
    
 
  };
  
  
  return(
    <form method="get" onSubmit={mySubmitHandler}>
        
        
        <h2> Appointment Cancellation:</h2>
        
        <br></br>
        <label>
        Select Reason For Canceling:
        <select name="Reason" id="Reason">
        <option value="">Select</option>
            <option value="Do not specify">Do not specify</option>
            <option value="Sickness">Sickness</option>
            <option value="Can't Make it">Can't Make it</option>
            <option value="Other commitments">Other commitments</option>
          </select>
          </label>
          <br></br>
        
        <br></br>
        <input type="submit" value="Confirm cancellation" />
      </form>
    
  )
  

  }

  
 

 


ReactDOM.render(<App />, document.querySelector("#root"));
