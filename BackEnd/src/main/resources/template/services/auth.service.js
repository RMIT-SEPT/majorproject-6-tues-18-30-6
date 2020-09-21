import axios from "axios";

const API_URL = "http://localhost:8080/";
//replace this line with the url for your postman mock, or use the above line instead to access the backend
//const API_URL = "https://85614fc4-77ad-44a6-bc0a-fa2f51509bf7.mock.pstmn.io/"

class AuthService {
  login(username, password) {
    
    return axios.post(API_URL + "login", {
        username,
        password
      })
      .then(response => {
        console.log(response)
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  registerUser(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  registerBusiness(businessName, ABN, category) {
    return axios.post(API_URL + "businessSignup", {
      businessName,
      ABN,
      category
    });
  }

  editDetails(username, email, password, address, phone){
    return axios.post(API_URL + "editDetails", {
      username,
      email,
      password,
      address,
      phone
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();