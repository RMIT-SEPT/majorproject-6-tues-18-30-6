import axios from 'axios';
import authHeader from './auth-header';

//const API_URL = "http://localhost:8080/api/auth/";
//replace this line with the url for your postman mock, or use the above line instead to access the backend
const API_URL = "https://d1acfa87-1e9b-4cd4-b66d-9481395b1b9e.mock.pstmn.io/"

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getWorkerBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  getBookingHistory(user){
    return axios.get(API_URL + 'bookingHistory', { headers: authHeader()}, {params: {user: user.username}})
  }
}

export default new UserService();
