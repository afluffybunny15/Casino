import axios from 'axios';

export const handleRegister = (registerObj) => {
    return axios
      .post('http://localhost:8080/auth/register', 
        registerObj
      )
      .catch((error) => {
        console.error('Login failed:', error);
        throw error;
      });
  };

export const handleLogin = (loginObj) => {

    return axios
      .post('http://localhost:8080/login', {
        loginObj
      })
      .then((response) => {
        console.log('Login successful:', response.data);
      })
      .catch((error) => {
        console.error('Login failed:', error);
      });
  };