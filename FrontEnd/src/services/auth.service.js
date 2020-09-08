import axios from "axios";

//const API_URL = "http://localhost:8080/api/auth/";
const API_URL = "https://bf4e702b-170d-418f-a6cb-1b30d87edbc2.mock.pstmn.io";

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

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();
