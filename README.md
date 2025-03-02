# Globetrotter Quiz App

## 📌 Overview
The **Globetrotter Quiz App** is a fun and interactive game where users guess destinations based on clues and trivia. It features AI-generated datasets, real-time feedback, a scoring system, and a "Challenge a Friend" feature.

## 🏗️ Tech Stack
- **Frontend:** React.js (Located in `./globetrotter-frontend`)
- **Backend:** Java Spring Boot (Maven) with MongoDB (Located in `./globetrotter-backend`)
- **Database:** MongoDB
- **AI Integration:** OpenAI API & Web Scraping for dataset expansion

---

## 🚀 How to Run the Project

### **1️⃣ Setting Up the Frontend (React.js)**
📍 **Navigate to the frontend directory**:
```sh
cd ./globetrotter-frontend
```

📦 **Install dependencies**:
```sh
npm install
```

▶ **Start the development server**:
```sh
npm start
```

🔨 **Build for deployment**:
```sh
npm run build
```
> This generates an optimized production build in the `build/` directory.

---

### **2️⃣ Setting Up the Backend (Spring Boot + MongoDB)**
📍 **Navigate to the backend directory**:
```sh
cd ./globetrotter-backend
```

📦 **Build the Spring Boot application**:
```sh
mvn clean package
```

▶ **Run the backend server**:
```sh
mvn spring-boot:run
```

🛢 **Ensure MongoDB is running** locally or provide a connection string in `application.properties`:
```
spring.data.mongodb.uri=mongodb://localhost:27017/globetrotterDB
```

---

## 🌍 Features

### **1️⃣ Dataset & AI Integration**
✅ Expand dataset to **100+ destinations** using AI tools (ChatGPT, OpenAI API, Web Scraping).
✅ Include **clues, fun facts, and trivia** for each destination.
✅ Store dataset securely on the backend (no client-side exposure!).

### **2️⃣ Core Gameplay Features**
✅ Display **1–2 random clues** from a destination.
✅ Allow users to **select an answer** from multiple options.
✅ Provide **immediate feedback**:
   - 🎉 **Correct Answer:** Show confetti + fun fact.
   - 😢 **Incorrect Answer:** Show sad-face animation + fun fact.
✅ ‘Play Again’ / ‘Next’ button for a **new random destination**.
✅ Track **total user score** (correct & incorrect answers).

### **3️⃣ “Challenge a Friend” Feature**
✅ User **registers with a unique username** before inviting friends.
✅ Clicking ‘Challenge a Friend’:
   - Generates a **shareable invite link** (WhatsApp, social media, etc.).
   - Creates a **dynamic image** with invitee’s score.
✅ Friends **see the invitee’s score before playing**.
✅ Anyone with the link can play with full features.

---

## 🛠 Deployment Guide

### **Deploying the Backend (Spring Boot) on Render**
1. **Create a new Render service** (Web Service) and select the repository.
2. **Set up environment variables** (MongoDB URI, etc.).
3. **Use the following build & start command**:
   ```sh
   mvn clean package && java -jar target/*.jar
   ```
4. **Deploy and monitor logs on Render dashboard.**

### **Deploying the Frontend (React) on Vercel/Netlify**
1. **Push frontend code to GitHub.**
2. **Connect GitHub repo to Vercel/Netlify.**
3. **Set build command:**
   ```sh
   npm run build
   ```
4. **Set output directory:** `build/`
5. **Deploy & configure domain settings.**

---