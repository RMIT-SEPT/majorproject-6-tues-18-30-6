import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";


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
