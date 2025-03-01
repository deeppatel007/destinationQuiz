import React from 'react';
import { motion } from 'framer-motion';
import { FaCheckCircle, FaTimesCircle } from 'react-icons/fa';

function Score(score) {
  return (
    <motion.div 
      className="score-container"
      animate={{ opacity: [0, 1], y: [-10, 0] }}
      transition={{ duration: 0.5 }}
    >
      <h2 className="score-title">Your Score</h2>

      <div className="score-details">
        <p className="correct">
          <FaCheckCircle className="icon correct-icon" /> Correct: <strong>{score.correctAnswers}</strong>
        </p>
        <p className="incorrect">
          <FaTimesCircle className="icon incorrect-icon" /> Incorrect: <strong>{score.incorrectAnswers}</strong>
        </p>
      </div>

      {/* Styling */}
      <style jsx>{`
        .score-container {
          background: rgba(255, 255, 255, 0.9);
          padding: 15px;
          border-radius: 12px;
          box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
          text-align: center;
          width: 280px;
          margin: 20px auto;
          font-family: 'Arial', sans-serif;
          animation: fadeIn 0.5s ease-in;
        }
        .score-title {
          font-size: 1.4rem;
          font-weight: bold;
          color: #333;
          margin-bottom: 10px;
        }
        .score-details {
          display: flex;
          justify-content: space-between;
          padding: 10px;
        }
        .correct, .incorrect {
          display: flex;
          align-items: center;
          gap: 8px;
          font-size: 1.2rem;
        }
        .correct-icon {
          color: #28a745;
          font-size: 1.5rem;
        }
        .incorrect-icon {
          color: #dc3545;
          font-size: 1.5rem;
        }
      `}</style>
    </motion.div>
  );
}

export default Score;
