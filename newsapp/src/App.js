import './App.css';
import React,{Component} from 'react';
import NavBar from './components/NavBar';
import News  from './components/News'
import {
  BrowserRouter as Router,
  Routes,
  Route
} from 'react-router-dom';


export default class App extends Component{
  c = "this is so over powered";

apiKey = process.env.REACT_APP_NEWS_API; 
  render(){
    return (
      <div>
        <Router>
          <NavBar />
        <Routes>
              <Route exact path="/"  element={<News apiKey={this.apiKey} key="general" pageSize={12} category="general"/>} />
              <Route exact path="/business"  element={<News apiKey={this.apiKey} key="business" pageSize={12} category="business"/>} />
              <Route exact path="/entertainment"  element={<News apiKey={this.apiKey} key="entertainment" pageSize={12} category="entertainment"/>} />
              <Route exact path="/general"  element={<News apiKey={this.apiKey} key="general" pageSize={12} category="general"/>} />
              <Route exact path="/health"  element={<News apiKey={this.apiKey} key="health" pageSize={12} category="health"/>} />
              <Route exact path="/science" element={<News apiKey={this.apiKey} key="scinene" pageSize={12} category="science"/>} />
              <Route exact path="/sports" element={<News apiKey={this.apiKey}  key="sports" pageSize={12} category="sports" />} />
              <Route exact path="/technology"  element={<News apiKey={this.apiKey} key="technology" pageSize={12} category="technology" />}/>

          </Routes>
          </Router>
      </div>
    )
  }
}