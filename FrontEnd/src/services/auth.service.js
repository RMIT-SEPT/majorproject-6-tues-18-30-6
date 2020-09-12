import axios from "axios";

//const API_URL = "http://localhost:8080/api/auth/";
//replace this with the address for your postman mock, or delete it and uncomment the above line to use the real backend
const API_URL = "https://d1acfa87-1e9b-4cd4-b66d-9481395b1b9e.mock.pstmn.io/"

class AuthService {
  login(username, password, userType) {
    
    return axios.post(API_URL + "signin", {
        username,
        password,
        userType
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

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();
