import axios from 'axios';

const API_BASE_URL = 'https://globetrotter-backend-plus-latest.onrender.com';

const api = {
  getRandomDestination: () => axios.get(`${API_BASE_URL}/api/questions/random`),
  registerUser: (user) => axios.post(`${API_BASE_URL}/api/users/register`, user),
  getUser: (username) => axios.get(`${API_BASE_URL}/api/users/${username}`),
  updateUserScore: (username, user) => axios.put(`${API_BASE_URL}/api/users/${username}/score`, user),
};

export default api;