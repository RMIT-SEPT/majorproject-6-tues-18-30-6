import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';


class SearchPage extends React.Component{
  constructor(props){
    super(props)
    this.state={
      times:[
        {
        minute: "30",
        hour  : "09",
        date  : "2020-08-05",
        inputMatch:true,
        advanceMatch:true,
        dateSelectMatch :true
      },
      {
        minute: "00",
        hour  : "10",
        date  : "2020-08-05",
        inputMatch:true,
        advanceMatch:true,
        dateSelectMatch :true
      },
      {
        minute: "15",
        hour  : "15",
        date  : "2020-08-05",
        inputMatch:true,
        advanceMatch:true,
        dateSelectMatch :true
      }
    
    ]
    }
  }

  searchInput=()=>{
    console.log("searchInput")
    let searchInput=document.getElementById("searchInput").value
    let copy=this.state.times.slice()
    for(let i=0;i<copy.length;i++){
      let time=copy[i].hour+":"+copy[i].minute
      copy[i].inputMatch= time===searchInput? true : false
    }
    console.log(copy)
    this.setState({
      times:copy
    })
  }

  dateSelect=()=>{
    console.log("dateSelect")
    let dateSelector=document.getElementById("dateSelector").value
    let copy=this.state.times.slice()
    for(let i=0;i<copy.length;i++){
      copy[i].dateSelectMatch= copy[i].date===dateSelector? true : false
    }
    console.log(copy)
    this.setState({
      times:copy
    })
  }

  advance=()=>{
    console.log("advance")
    let advance=document.getElementById("advance")
    let copy=this.state.times.slice()
    for(let i=0;i<copy.length;i++){
      if(advance.options[advance.selectedIndex].text === "morning"){
        copy[i].advanceMatch= parseInt(copy[i].hour)<12? true : false
      }
      else if(advance.options[advance.selectedIndex].text === "afternoon"){
        copy[i].advanceMatch= parseInt(copy[i].hour)>=12? true : false
      }
      else{
        copy[i].advanceMatch=true
      }
    }
    console.log(copy)
    this.setState({
      times:copy
    })
  }


  render(){
    //time lists to be rendered
    let timeLists=this.state.times.map((item,index)=>{
      if(item.advanceMatch && item.dateSelectMatch && item.inputMatch){
        return(
          <div  class="row" key={index}>
            <div class="col-10 listItem">
              <span>{item.date}</span>
              &nbsp;
              <span>{item.hour+":"+item.minute}</span>
            </div>

            <div class="col-2">
              <button type="button" class="btn btn-primary">Register</button>
            </div>
          </div>
          )
      }else{
        return null
      }
    })



    return(
      <div class="container">
 
            <h1>Search</h1>
    


       <div class="row main">
          <div class="col">
            <div  class="input-group mb-3">
              <input id="searchInput" type="text" class="form-control" placeholder="Search a Time" />
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-addon2" onClick={this.searchInput}>Search</button>
              </div>
            </div>
          </div>
        </div>

  
        <div class="row main">
          <div  class="offset-4 col-4">
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

        <div class="main">
           {timeLists}
        </div>
         
        
 
      </div>
    )
  }

}





ReactDOM.render(
  <SearchPage />,
  document.getElementById('root')
);

