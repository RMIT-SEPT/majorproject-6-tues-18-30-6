import axios from 'axios';
import authHeader from './auth-header';

const API_URL = "http://localhost:8080/api/";
//replace this line with the url for your postman mock, or use the above line instead to access the backend
//const API_URL = "https://85614fc4-77ad-44a6-bc0a-fa2f51509bf7.mock.pstmn.io/"

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getDetails(user){
    return axios.get(API_URL + 'user/getDetails', {headers: authHeader()}, {params: {user: user.username}});
  }

  getWorkerBoard() {
    return axios.get(API_URL + 'worker', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  getBookingHistory(user){
    return axios.get(API_URL + 'booking/bookingHistory', { headers: authHeader()}, {params: {user: user.username}})
  }
}

export default new UserService();
