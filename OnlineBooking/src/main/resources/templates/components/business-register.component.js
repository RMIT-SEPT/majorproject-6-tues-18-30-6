import React from 'react';
import AuthService from "../services/auth.service";


export default class BusinessRegistration extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      businessName: '',
      abn: null,
      category: null,
      successful: false,
      message: ""
    };
  }

  submitHandler = (event) => {
    let abn = this.state.abn;
    let businessName = this.state.businessName;
    let category = this.state.category;
    if (!Number(abn) || abn.length!==11){
      alert("Your ABN must be a number and be 11 digits long");
      event.preventDefault();
    }
    if (businessName===''){
      alert("Business name can't be empty")
      event.preventDefault();
    }
    if (category==null){
      alert("Business category must be selected")
      event.preventDefault();
    }  

    AuthService.registerBusiness(
        this.state.businessName,
        this.state.abn,
        this.state.category
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            successful: false,
            message: resMessage
          });
        }
      );

  }

  myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({[nam]: val});
  }

  render() {
    return (
      <form method="get" onSubmit={this.submitHandler}>
      <h1>Business Registration Page </h1>
      <p>Enter your Business Name:</p>
      <input
        type='text'
        name='businessName'
        onChange={this.myChangeHandler}
        placeholder='Enter your business name'
      />
      <p>Enter your ABN:</p>
      <input
        type='text' name='abn'
        title="An ABN is 11 digits long"
        onChange={this.myChangeHandler}
        placeholder='ABN must be 11 digits'
      />
      <p>Select your type of business category:</p>
      <select name="category" onChange={this.myChangeHandler}>
      <option value="" name="category">Select Category</option>
    <option value="Lodging" name="category">Lodging</option>
    <option value="Food and Drink" name="category">Food and Drink</option>
    <option value="Event Planning" name="category">Event Planning</option>
    <option value="Personal Care" name="category">Personal Care</option>
    
  </select>
      <br/>
      <br/>
      <input type='submit' />
      </form>
    );
  }
}
