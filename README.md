🧠 AI Code Review System

Tech Stack: Java • Spring Boot • Gemini API • JavaScript • HTML/CSS

📌 Overview

The AI Code Review System is a web-based application that allows developers to paste a code snippet, send it to the backend, and instantly receive an AI-generated review suggesting optimizations, improvements, and security feedback.

It demonstrates backend–AI integration using Spring Boot and Gemini API, focusing on clean architecture, input validation, and efficient API communication.

⚙️ Features

🔍 Paste any code snippet (Java, Python, JS, etc.)

🧩 Backend sends the snippet to Gemini API for analysis

💡 Returns structured feedback — readability, performance, and security tips

🚦 Input validation and rate-limiting to prevent abuse

🧱 Layered backend architecture (Controller → Service → API Client)

📦 Modular design for easy extension to other AI APIs

🏗️ Project Architecture<br/>

Frontend (HTML + JavaScript)<br/>

        │
        ▼
Spring Boot Backend (REST API)<br/>

        │
        ▼
Gemini API (AI Feedback Generation)<br/>


🧰 Tech Stack<br/>

Layer	Technology
Backend	Java 17, Spring Boot 3.x
Frontend	HTML, CSS, JavaScript
AI Integration	Gemini API (Google Generative AI)
Build Tool	Maven
Testing	Postman / Curl
Version Control	Git, GitHub
🧩 API Workflow<br/>


User Input: The frontend captures a code snippet from a text area.

POST Request: Frontend sends the code to /api/review endpoint.

Backend Processing:

Validates input size and format.

Sends prompt + code to Gemini API.

Receives structured feedback (e.g., optimization, security tips).

Response: Backend returns formatted AI feedback as JSON.

Frontend Display: The feedback appears in a styled result box.

🚀 How to Run Locally
1️⃣ Clone Repository
git clone https://github.com/RakeshKayal/code-Review-System.git<br/>
cd AI-Code-Review-System
<br/>

2️⃣ Configure Gemini API Key
<br/>


Create a .env or application.properties file:<br/>

GEMINI_API_KEY=your_api_key_here
<br/>


3️⃣ Run Spring Boot App
<br/>

mvn spring-boot:run
<br/>


Backend will start on:<br/>

http://localhost:8080
