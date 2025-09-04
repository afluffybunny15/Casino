import {React, useState} from 'react';
import { handleRegister } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';//Use axios for the post request
const RegisterComponent= () => {

    const [username, setUsername] = useState(''); //store the username of user
    const [email, setEmail] = useState(''); //store the email of user 
    const [password, setPassword] = useState(''); //store the password of the user 
    //const [errorMessage, setErrorMessage] = useState(''); //store the error messages 
    const navigate = useNavigate();

    function handleRegisterForm(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        const register = {username, email, password};
        handleRegister(register).then(() => {
            navigate('/login');
        }).catch((error) => {
            console.error('Registration failed:', error);
        })
    }
    

      return (
        <div className="RegisterForm">
          <form onSubmit={handleRegisterForm}>
            <h2>Register</h2>
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
                <div className='col-md-9'>
                    <input
                        type='text'
                        name='Email'
                        className='form-control'
                        placeholder='Enter Email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
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
            <button type="submit">Register</button>
          </form>
          
        </div>
        
      );
};



export default RegisterComponent;
