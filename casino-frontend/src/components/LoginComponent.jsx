import {React, useState} from 'react';
import { handleLogin } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';
//Use axios for the post request
const LoginComponent= () => {
    const [username, setUsername] = useState(''); //store the username of user
    const [password, setPassword] = useState(''); //store the password of the user 
    //const [errorMessage, setErrorMessage] = useState(''); //store the error messages 
    const navigate = useNavigate();
    
    function handleLoginForm(event) {
            event.preventDefault(); // Prevent the default form submission behavior
            const login = {username, password};
            handleLogin(login).then(() => {
                navigate('/casino');
            }).catch((error) => {
                console.error('Login failed:', error);
            })
        }

      return (
        <div className="LoginForm">
          <form onSubmit={handleLoginForm}>
            <h2>Login</h2>
            <div className='row mb-3'>
                <label className='col-md-3 control-label'>Username</label>
                <div className='col-md-9'>
                    <input
                        type='text'
                        name='Username'
                        className='form-control'
                        placeholder='Enter Username'
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <label className='col-md-3 control-label'>Email</label>
                
                <label className='col-md-3 control-label'>Password</label>
                <div className='col-md-9'>
                    <input
                        type='password'
                        name='Password'
                        className='form-control'
                        placeholder='Enter Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                
            </div>
          <div className="RegisterLink">
            <p>Don't have an account? <a href="/register">Register here</a></p>
        </div>
        <button type="submit">Login</button>
        </form>
        </div>
        
      );
};



export default LoginComponent;
