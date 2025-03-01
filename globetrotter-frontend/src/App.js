import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import Game from './components/Game';
import api from './services/api';
import Title from './components/Title';

function App() {

return (
       
      <div >
      <Title/>
          <Game />
      </div>
  );
}

export default App;