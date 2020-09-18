import axios from "axios";

//const API_URL = "http://localhost:8080/api/auth/";
//replace this line with the url for your postman mock, or use the above line instead to access the backend
const API_URL = "https://d1acfa87-1e9b-4cd4-b66d-9481395b1b9e.mock.pstmn.io/"

class AuthService {
  login(username, password, userType) {
    return axios
      .post(API_URL + "signin", {
        username,
        password,
        userType
      })
      .then(response => {
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