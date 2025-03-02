import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import api from '../services/api';

function Title() {
    const [inviteeScore, setInviteeScore] = useState(null);
    const [inviteeUsername, setInviteeUsername] = useState(null);
  
    useEffect(() => {
      const urlParams = new URLSearchParams(window.location.search);
      const username = urlParams.get('invite');
  
      if (username) {
        setInviteeUsername(username);
        fetchInviteeScore(username);
      }
    }, [window.location.search]);
  
    const fetchInviteeScore = async (username) => {
      try {
        const response = await api.getUser(username);
        if (response?.data) {
          setInviteeScore({
            username: response.data.username || username,
            correctAnswers: response.data.correctAnswers || 0,
            incorrectAnswers: response.data.incorrectAnswers || 0,
          });
        } else {
          console.warn('No data received for invitee:', username);
        }
      } catch (error) {
        console.error('Error fetching invitee score:', error);
      }
    };
  
    return (
        <div
  style={{
    minHeight: "20vh",
    color: "white",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  }}
>
        {/* Navigation Bar */}
        <nav className="w-full  shadow-md p-4 flex justify-center">
          <motion.h1
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5 }}
            className="text-3xl font-bold text-white px-6 py-2 rounded-lg"
          >
            Globetrotter Challenge
          </motion.h1>
        </nav>
  
        <div className="p-8 w-full flex flex-col items-center" style={
            {"textAlign":"center"}}>
          {/* Invitee Score Section */}
          {inviteeScore && (
            <motion.div
              initial={{ opacity: 0, scale: 0.9 }}
              animate={{ opacity: 1, scale: 1 }}
              transition={{ duration: 0.5 }}
              className="bg-white text-black p-6 rounded-xl shadow-xl w-80 text-center mt-6"
            >
              <h2 className="text-2xl font-semibold mb-2 text-blue-600">
                Invitee's Score
              </h2>
              <h3 className="text-lg font-bold">Invitee's Name: {inviteeScore.username}</h3>
              <h3 className="mt-2 text-xl">
                ✅ <span className="text-green-500 font-bold">{inviteeScore.correctAnswers}</span> | ❌ <span className="text-red-500 font-bold">{inviteeScore.incorrectAnswers}</span>
              </h3>
            </motion.div>
          )}
        </div>
      </div>
    );
  }
  
  export default Title;