import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

<<<<<<< HEAD
  getModeratorBoard() {
    return axios.get(API_URL + 'worker', { headers: authHeader() });
=======
  getWorkerBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
>>>>>>> dev
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
