import './App.css';
import React from 'react';
import Main from "./views/MainLayout"
import {CookiesProvider} from "react-cookie";

const App = () => {

  return (
      <CookiesProvider>
        <Main/>
      </CookiesProvider>
  )
}

export default App;
