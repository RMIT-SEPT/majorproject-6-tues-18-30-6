import axios from "axios";

const API_URL = "http://localhost:8080/api/bookings/";
//replace this line with the url for your postman mock, or use the above line instead to access the backend
//const API_URL = "https://85614fc4-77ad-44a6-bc0a-fa2f51509bf7.mock.pstmn.io/"

class BookingService {
    getAllBusinesses() {
        return axios.get(API_URL + "getAllBusinesses");
    }

    getAvailableBookings(id) {
        return axios.post(API_URL + "getAvailableBookings", {id});
      }
}

export default new BookingService();