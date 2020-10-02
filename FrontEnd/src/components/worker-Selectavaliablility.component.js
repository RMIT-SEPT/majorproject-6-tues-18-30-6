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
        
        
        <h2>Please provide working avaliability times and dates:</h2>
        <label>
          Select Date Then Select Your Avaliable Times:
        <br></br>
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
          
         

        
        <br></br>
       
        <label>Please select avaliable working shift(s):</label>
        <br></br>
        <br></br>
       
     <input type="checkbox" class="Timee" name="Time1" value='10-00AM - 11-00AM' /> 10:00AM - 11:00AM
     <br></br>
     <input type="checkbox" class="Timee" name="Time2" value='11-00AM - 12-00PM'/> 11:00AM - 12:00PM
     <br></br>
     <input type="checkbox" class="Timee" name="Time3" value='12-00PM - 1-00PM'/> 12:00PM - 1:00PM
     <br></br>
     <input type="checkbox" class="Timee" name="Time4" value='1-00PM - 2-00PM'/> 1:00PM - 2:00PM
     <br></br>
     <input type="checkbox" class="Timee" name="Time5" value='2-00PM - 3-00PM'/> 2:00PM - 3:00PM
     <br></br>
     <input type="checkbox" class="Timee" name="Time6" value='3-00PM - 5-00PM'/> 3:00PM - 5:00PM
     <br></br>
     <input type="checkbox" name="ApplyNextWeek" /> Would you like to apply these times to avaliability to next week? (Monday to Friday)
   <br>
   </br>
   <br>
   </br>
       
        <input type="text" id="dateWeek" name="dateWeek" value=""  hidden />
        <input type="submit" value="Update Avaliability" />
       
      </form>
    
  )
  

  }

  
 

 


ReactDOM.render(<App />, document.querySelector("#root"));
