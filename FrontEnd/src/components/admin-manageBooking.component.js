//Note: this is to install the datepicker package- npm install react-datepicker --save 

import React, { useState } from "react";
import ReactDOM from "react-dom";
import './style.css';

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";



  function App () {
    const [selectedDate, setSelectedDate] = useState(null)
   

  function mySubmitHandler(e) {
    
    var curr = new Date;
    var first = curr.getDate() - curr.getDay()+8; 
    var month = new Date().getMonth() + 1;
    var last = first +5;
    
    
    document.getElementById("dateWeek").value = first +"-"+ month + "-" + last +"-";
    
     
   if(last > 31){

    document.getElementById("dateWeek").value = first +"-"+ month + "-" + (last-30) +"-" + (month+1);
   }


    if (document.getElementById("Datee").value === "") {
     
     
      alert("A date must be selected");
      e.preventDefault();
    }

    var checked = 0;
  for (var i = 0; i < document.getElementsByClassName("Timee").length; i++){
    
    if(document.getElementsByClassName("Timee")[i].checked == true){
       checked +=1;
    }
  }
    if (checked == 0) {
      
      alert("At least one avaliable time must be selected");
      e.preventDefault();
    
  }
};
   
  return(
      
    
    <form method="get" onSubmit={mySubmitHandler}>
        
        
        <h2>Manage Bookings:</h2>
       
         <h4>Select Checkbox in Cancel Booking Section and Submit to Cancel that Customers Booking: </h4> 
         <table id="Bookings">
  <tr>
    <th>Client Name</th>
    <th>Phone Number</th>
    <th>Booking Date</th>
    <th>Booking Time</th>
    <th>Specified Worker</th>
    <th>Cancel Booking</th>
  </tr>
  <tr>
    <td>Alfred Johnnsom</td>
    <td>0240781422</td>
    <td>30/09/2020</td>
    <td>9:00AM</td>
    <td>Wanda</td>
    <td><input type="checkbox" name="book1"/></td>
  </tr>
  <tr>
    <td>Frederick Simms</td>
    <td>0240371645</td>
    <td>1/10/2020</td>
    <td>10:30AM</td>
    <td>Wilfred</td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Daniel Bertrand</td>
    <td>0753580416</td>
    <td>2/10/2020</td>
    <td>10:30AM</td>
    <td>Blake</td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Eric Handel</td>
    <td>0734272614</td>
    <td>2/10/2020</td>
    <td>10:30AM</td>
    <td>Fred</td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>James Dunn</td>
    <td>0890823254</td>
    <td>2/10/2020</td>
    <td>10:30AM</td>
    <td> </td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Brad Essen</td>
    <td>0240405242</td>
    <td>3/10/2020</td>
    <td>11:30AM</td>
    <td> </td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Henry Davidson</td>
    <td>0887775364</td>
    <td>3/10/2020</td>
    <td>11:30AM</td>
    <td></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Bobby Johnson</td>
    <td>0266379705</td>
    <td>4/10/2020</td>
    <td>9:30AM</td>
    <td></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Mohammad Aamer</td>
    <td>0894024325</td>
    <td>4/10/2020</td>
    <td>10:00AM</td>
    <td>Brad</td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Alfred Hitchcock</td>
    <td>0267094409</td>
    <td>4/10/2020</td>
    <td>10:00AM</td>
    <td>Wanda</td>
    <td><input type="checkbox"/></td>
  </tr>
</table>
<input type="submit" value="Cancel Booking(s)"/>
          </form>
    
  )
  

  }

  
 

 


ReactDOM.render(<App />, document.querySelector("#root"));
