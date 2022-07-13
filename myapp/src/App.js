
import './App.css';
import Navbar from './components/Navbar';
import Textbox from './components/Textbox';
import React,{useState} from 'react';
import Alert from './components/Alert';
import About from './components/About';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from 'react-router-dom';

function App() {
  const [mode,setMode] = useState('light');
  const [alert,setAlert] = useState(null);

  const showAlert =(message,type)=>{
    setAlert({
      message:message,
      type : type
    })
    setTimeout(() => {
      setAlert(null);
    }, 1500);
  }
  const toggleMode = ()=>
  {
    if(mode==='light')
    {
      setMode('dark');
      document.body.style.background = 'black';
      showAlert('Dark mode is enabled','success');
    }
    else{
      setMode('light');
      document.body.style.background = 'white';
      showAlert('Light mode is enabled','success');
    }
  }
  return (
    <>
    <Router>
        <Navbar title="TEXTUTIL" mode={mode}  toggleMode={toggleMode} />
        < Alert alert={alert}/>
        
        <Routes>
            <Route path="/about" element={<About/>} />
            
          <Route path="/" element={<Textbox heading="Enter the text to modify!" showAlert={showAlert} mode={mode}/>} />          
     </Routes>
        
      </Router>
   </>
  );
}

export default App;
