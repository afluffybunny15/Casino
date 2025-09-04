import './App.css';
import LoginComponent from './components/LoginComponent';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import RegisterComponent from './components/RegisterComponent';
import HomepageComponent from './components/HomepageComponent';
function App() {
  return (
    <BrowserRouter>
        <div className="main-content">
          <Routes>
            <Route path='/' element={<LoginComponent />} />
            <Route path='/login' element={<LoginComponent/>} />
            <Route path='/register' element={<RegisterComponent/>} />
            <Route path='/casino' element={<HomepageComponent/>} />
          </Routes>
        </div>
    </BrowserRouter>
        
  );
}

export default App;
