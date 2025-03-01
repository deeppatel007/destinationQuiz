import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = {
  getRandomDestination: () => axios.get(`${API_BASE_URL}/questions/random`),
  registerUser: (user) => axios.post(`${API_BASE_URL}/users/register`, user),
  getUser: (username) => axios.get(`${API_BASE_URL}/users/${username}`),
  updateUserScore: (username, user) => axios.put(`${API_BASE_URL}/users/${username}/score`, user),
};

export default api;