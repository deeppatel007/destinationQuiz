import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import Confetti from 'react-confetti';
import { FaSadTear } from 'react-icons/fa';
import api from '../services/api';
import Score from './Score';
import Challenge from './Challenge';

function Game() {
  const [destination, setDestination] = useState(null);
  const [selectedOption, setSelectedOption] = useState('');
  const [feedback, setFeedback] = useState('');
  const [score, setScore] = useState({ correctAnswers: 0, incorrectAnswers: 0 });
  const [inviteeScore, setInviteeScore] = useState({ correctAnswers: 0, incorrectAnswers: 0 });
  const [showConfetti, setShowConfetti] = useState(false);
  const [isAnswered, setIsAnswered] = useState(false);
  const [shake, setShake] = useState(false);
  const [username, setUsername] = useState(null);

  useEffect(() => {
    fetchRandomDestination();
    getUsername();
  }, []);

  const getUsername = () => {
    const urlParams = new URLSearchParams(window.location.search);
    const inviteeUsername = urlParams.get('invite');
    const storedUsername = localStorage.getItem('username');
    setUsername(inviteeUsername || storedUsername);
  };

  const fetchUserScore = async (username) => {
    if (!username) return;
    try {
      const response = await api.getUser(username);
      setInviteeScore({ correctAnswers: response.data.correctAnswers, incorrectAnswers: response.data.incorrectAnswers });
    } catch (error) {
      console.error('Error fetching user score:', error);
    }
  };

  const fetchRandomDestination = async () => {
    try {
      const response = await api.getRandomDestination();
      setDestination(response.data);
      setFeedback('');
      setSelectedOption('');
      setShowConfetti(false);
      setIsAnswered(false);
      setShake(false);
    } catch (error) {
      console.error('Error fetching destination:', error);
    }
  };

  useEffect(() => {
    if (username) {
      fetchUserScore(username);
    }
  }, [username]);

  useEffect(() => {
    if (username) {
      fetchUserScoreCached(username);
    }
  }, [username]);

  useEffect(() => {
    if (username) {
      localStorage.setItem(`score_${username}`, JSON.stringify(score));
    }
  }, [score]);

  const fetchUserScoreCached = async (username) => {
    if (!username) return;
    const storedScore = localStorage.getItem(`score_${username}`);
    if (storedScore) {
      setScore(JSON.parse(storedScore));
      return;
    }
  };

  const checkAnswer = () => {
    if (!destination) return;

    let updatedScore = { ...score };

    if (selectedOption.toLowerCase() === destination.city.toLowerCase()) {
      setFeedback(`🎉 Correct! ${destination.fun_fact[0]}`);
      updatedScore.correctAnswers += 1;
      setShowConfetti(true);
    } else {
      setFeedback(`❌ Incorrect. The answer was ${destination.city}. ${destination.fun_fact[0]}`);
      updatedScore.incorrectAnswers += 1;
      setShake(true);
      setTimeout(() => setShake(false), 500);
    }

    setIsAnswered(true);
    setScore(updatedScore);
    console.log("score", updatedScore);
  };

  return (
    <div className="game-container">
      {showConfetti && <Confetti />}
      {destination && (
        <motion.div
          className="game-card"
          animate={{ opacity: [0, 1], y: [-20, 0] }}
          transition={{ duration: 0.5 }}
        >
          <h2 className="title">🌍 Guess the Destination!</h2>
          <p className="clue">{destination.clues[0]}</p>

          <div className="options-container">
            {destination.options.map((option) => (
              <motion.button
                key={option}
                className={`btn option-btn ${selectedOption === option ? 'selected' : ''}`}
                onClick={() => setSelectedOption(option)}
                whileTap={{ scale: 0.95 }}
              >
                {option}
              </motion.button>
            ))}
          </div>

          {!isAnswered ? (
            <motion.button
              className="btn submit-btn"
              onClick={checkAnswer}
              whileTap={{ scale: 0.9 }}
            >
              Submit
            </motion.button>
          ) : (
            <motion.p className="feedback">
              {feedback} {!showConfetti && <FaSadTear className="sad-icon" />}
            </motion.p>
          )}

          {isAnswered && (
            <motion.button
              className="btn next-btn"
              onClick={fetchRandomDestination}
              whileTap={{ scale: 0.9 }}
            >
              Next ➡
            </motion.button>
          )}
          <p className="score">✅ Correct: {score.correctAnswers} | ❌ Incorrect: {score.incorrectAnswers}</p>
        </motion.div>
      )}

     

      <style jsx>{`
        .game-container {
      display: flex;
      justify-content: space-around;
      align-items: center;
      width: 100%;
      max-width: auto;
      gap: 30px;
    }
        .game-card {
          background: white;
          padding: 20px;
          border-radius: 12px;
          box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
          text-align: center;
          width: 350px;
          animation: fadeIn 0.5s ease-in;
        }
        .title {
          font-size: 1.5rem;
          color: #333;
          margin-bottom: 10px;
          background-color: #e0f7ff; /* Added background color */
          padding: 10px; /* Add padding for better visual */
          border-radius: 6px; /* Add border radius for better visual */
        }
        .clue {
          font-size: 1.2rem;
          color: #555;
          margin-bottom: 15px;
        }
        .options-container {
          display: flex;
          flex-direction: column;
          align-items: center;
          margin-bottom: 15px;
        }
        .option-btn {
          margin: 5px 0;
          padding: 8px 15px;
          font-size: 1rem;
          border: 1px solid #007bff;
          background: white;
          color: #007bff;
          border-radius: 6px;
          cursor: pointer;
          transition: all 0.3s ease;
          width: 80%;
        }
        .option-btn.selected {
          background: #007bff;
          color: white;
        }
        .option-btn:hover {
          background: #e0f7ff;
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
        .submit-btn {
          background: #28a745;
          color: white;
        }
        .submit-btn:hover {
          background: #218838;
        }
        .next-btn {
          background: #007bff;
          color: white;
          margin-top: 10px;
        }
        .next-btn:hover {
          background: #0056b3;
        }
        .feedback {
          margin-top: 15px;
          font-size: 1.1rem;
          color: #d9534f;
          font-weight: bold;
        }
        .sad-icon {
          font-size: 1.5rem;
          vertical-align: middle;
        }
        .score {
          margin-top: 20px;
          font-size: 1.2rem;
          font-weight: bold;
          color: #333;
        }
      `}</style>
      <Challenge score={score} />
    </div>
  );
}

export default Game;