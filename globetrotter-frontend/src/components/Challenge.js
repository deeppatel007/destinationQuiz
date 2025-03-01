import React, { useState } from 'react';
import { motion } from 'framer-motion';
import api from '../services/api';
import { FaWhatsapp } from 'react-icons/fa';

function Challenge({ score }) {
  const [username, setUsername] = useState('');
  const [isRegistered, setIsRegistered] = useState(false);
  const [errorMessage, setErrorMessage] = useState(''); // Add error state

  const registerUser = async () => {
    console.log("registering user");
    try {
      const newUser = {
        username: username,
        correctAnswers: score.correctAnswers,
        incorrectAnswers: score.incorrectAnswers,
      };
      console.log(newUser);
      await api.registerUser(newUser);
      setIsRegistered(true);
      setErrorMessage(''); // Clear error message on success
    } catch (error) {
      console.error('Error registering user:', error);
        setErrorMessage('User same username already exist please try another username');
      
    }
  };

  const shareInvite = () => {
    const inviteLink = `${window.location.origin}?invite=${username}`;
    const message = `Play Globetrotter with me! 🌍 ${inviteLink}`;
    window.open(`https://wa.me/?text=${encodeURIComponent(message)}`);
  };

  return (
    <motion.div
      className="challenge-container"
      animate={{ opacity: [0, 1], y: [-20, 0] }}
      transition={{ duration: 0.5 }}
    >
      <h2 className="title">🎯 Challenge a Friend!</h2>

      <input
        type="text"
        className="input-field"
        placeholder="Enter your username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        disabled={isRegistered}
      />

      {!isRegistered ? (
        <>
          <motion.button
            className="btn register-btn"
            onClick={registerUser}
            whileTap={{ scale: 0.9 }}
          >
            Register
          </motion.button>
          {errorMessage && <p className="error-message">{errorMessage}</p>} {/* Display error message */}
        </>
      ) : (
        <motion.p className="registered-message">
          ✅ Registered as <strong>{username}</strong>! Now challenge a friend 👇
        </motion.p>
      )}

      {isRegistered && (
        <motion.button
          className="btn invite-btn"
          onClick={shareInvite}
          whileTap={{ scale: 0.9 }}
        >
          <FaWhatsapp className="whatsapp-icon" /> Invite via WhatsApp
        </motion.button>
      )}

      {/* Styling */}
      <style jsx>{`
        .challenge-container {
          background: white;
          padding: 20px;
          border-radius: 12px;
          box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
          text-align: center;
          width: 350px;
          animation: fadeIn 0.5s ease-in;
          margin-top: 20px;
        }
        .title {
          font-size: 1.5rem;
          color: #333;
          margin-bottom: 15px;
        }
        .input-field {
          width: 80%;
          padding: 10px;
          font-size: 1rem;
          border-radius: 6px;
          border: 1px solid #ccc;
          outline: none;
          text-align: center;
          margin-bottom: 10px;
        }
        .btn {
          margin-top: 10px;
          padding: 10px 20px;
          font-size: 1rem;
          border: none;
          border-radius: 8px;
          cursor: pointer;
          transition: all 0.3s ease;
        }
        .register-btn {
          background: #28a745;
          color: white;
        }
        .register-btn:hover {
          background: #218838;
        }
        .invite-btn {
          background: #25D366;
          color: white;
          display: flex;
          align-items: center;
          justify-content: center;
          gap: 8px;
          font-weight: bold;
        }
        .invite-btn:hover {
          background: #1EBE57;
        }
        .whatsapp-icon {
          font-size: 1.3rem;
        }
        .registered-message {
          margin-top: 10px;
          font-size: 1rem;
          color: #333;
        }
        .error-message {
          color: red;
          margin-top: 10px;
        }
      `}</style>
    </motion.div>
  );
}

export default Challenge;