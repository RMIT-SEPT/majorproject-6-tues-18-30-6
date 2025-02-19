import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";


const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const email = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = value => {
  if(value != null){
    if (value.length < 3 || value.length > 20) {
      return (
        <div className="alert alert-danger" role="alert">
          The username must be between 3 and 20 characters.
        </div>
      );
    }
  }
};

const vpassword = value => {
  if(value != null){
  if ((value.length > 0) && (value.length < 6 || value.length > 40)) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
}
};

const vphone = value => {
  if(value != null){
  if (value.length !== 10 && value.length !== 0) {
    return (
      <div className="alert alert-danger" role="alert">
        Phone number must be exactly 10 characters.
      </div>
    );
  }
}
}

export default class EditDetails extends Component {
  constructor(props) {
    super(props);
    this.handleDetails = this.handleDetails.bind(this);
    this.onChangeUsername = this.onChangeUsername.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeAddress = this.onChangeAddress.bind(this);
    this.onChangePhone = this.onChangePhone.bind(this);

    this.state ={
      id: 0,
      username: "",
      email: "",
      password: "",
      address: "",
      phoneNumber: "",
      successful: false,
      message: ""
    };

    UserService.getDetails(AuthService.getCurrentUser()).then(
      response => {
        this.setState({
          id: response.data.id,
          username: response.data.username,
          email: response.data.email,
          password: "",
          address: response.data.address,
          phoneNumber: response.data.phoneNumber,
          roles: response.data.roles,
          successful: false,
          message: ""
        });
        console.log(response)
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

  onChangeUsername(e) {
    this.setState({
      username: e.target.value
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  onChangeAddress(e) {
    this.setState({
      address: e.target.value
    });
  }

  onChangePhone(e) {
    this.setState({
      phoneNumber: e.target.value
    });
  }

  handleDetails(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      AuthService.editDetails(
        this.state.id,
        this.state.username,
        this.state.email,
        this.state.password,
        this.state.address,
        this.state.phoneNumber,
        this.state.roles
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
          localStorage.setItem("user", JSON.stringify(response.data));
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
  }

  render() {
    return (
          <Form
            onSubmit={this.handleDetails}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <label htmlFor="username">Username</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="username"
                    value={this.state.username}
                    onChange={this.onChangeUsername}
                    validations={[required, vusername]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChangeEmail}
                    validations={[required, email]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[vpassword, required]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="address">Address</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="address"
                    value={this.state.address}
                    onChange={this.onChangeAddress}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="phone">Phone Number</label>
                  <Input
                    type="tel"
                    className="form-control"
                    name="phone"
                    value={this.state.phoneNumber}
                    onChange={this.onChangePhone}
                    validations={[vphone]}
                  />
                </div>

                <div className="form-group">
                  <button className="btn btn-primary btn-block">Confirm Details</button>
                </div>
              </div>
            )}

            {this.state.message && (
              <div className="form-group">
                <div
                  className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert"
                >
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
    );
  }
}
