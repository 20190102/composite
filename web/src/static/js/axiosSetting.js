
import Axios from 'axios'
Axios.defaults.baseURL = 'http://localhost:8080/composite';
Axios.defaults.headers.common['Authorization'] = localStorage.token;
Axios.interceptors.response.use(
  response => {
    console.log(response)
    if (response.data.token) {
        localStorage.token=response.data.token
    }
    return response
  }
)