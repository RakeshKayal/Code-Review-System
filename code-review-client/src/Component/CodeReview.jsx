import React, { useState, useRef, useEffect } from "react";
import toast from "react-hot-toast";
import { sendCode } from "../Service/ApiService";
import "./CodeReview.css";

const CodeReview = () => {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState([]);
  const [loading, setLoading] = useState(false);
  const chatEndRef = useRef(null);
  const MAX_WORDS = 200;

  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages, loading]);

  const handleInputChange = (e) => {
    const words = e.target.value.split(/\s+/);
    if (words.length <= MAX_WORDS) {
      setInput(e.target.value);
    } else {
      // Trim to max words
      setInput(words.slice(0, MAX_WORDS).join(" "));
      toast.error(`Maximum ${MAX_WORDS} words allowed!`);
    }
  };

  const handleSend = async () => {
    if (!input.trim()) {
      toast.error("Please enter some code!");
      return;
    }

    const userMessage = { role: "user", content: input };
    setMessages((prev) => [...prev, userMessage]);
    setInput("");

    try {
      setLoading(true);
      const res = await sendCode({ content: userMessage.content });
      const botMessage = { role: "assistant", content: res.data };
      setMessages((prev) => [...prev, botMessage]);
    } catch (err) {
      toast.error("Error reviewing code");
    } finally {
      setLoading(false);
    }
  };

  const wordCount = input.trim() === "" ? 0 : input.trim().split(/\s+/).length;

  return (
    <div className="code-review-container">
      <h2 className="title">Code Review</h2>

      <div className="chat-box">
        {messages.map((msg, i) => (
          <div key={i} className={`chat-message ${msg.role}`}>
            <pre>{msg.content}</pre>
          </div>
        ))}

        {loading && (
          <div className="typing">
            <span className="dot"></span>
            <span className="dot"></span>
            <span className="dot"></span>
          </div>
        )}

        <div ref={chatEndRef} />
      </div>

      <div className="input-bar">
        <textarea
          className="input-textarea"
          placeholder="Paste your code here..."
          value={input}
          onChange={handleInputChange}
        ></textarea>
        <div className="word-count">{wordCount}/{MAX_WORDS}</div>

        <button className="send-btn" disabled={loading} onClick={handleSend}>
          {loading ? "..." : "Send"}
        </button>
      </div>
    </div>
  );
};

export default CodeReview;
