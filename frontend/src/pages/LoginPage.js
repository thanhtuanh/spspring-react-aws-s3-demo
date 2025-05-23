import React, { useState } from 'react';
import axios from '../services/api';
import { useNavigate } from 'react-router-dom';

export default function LoginPage({ setIsAuthenticated }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');

    try {
      const res = await axios.post('/auth/login', { username, password });
      localStorage.setItem('token', res.data.token);
      setIsAuthenticated(true);
      navigate('/home');
    } catch (err) {
      setMessage('❌ Login fehlgeschlagen.');
      console.error(err);
    }
  };

  return (
    <div className="d-flex vh-100 align-items-center justify-content-center bg-light">
      <div className="card shadow-sm p-4" style={{ minWidth: '350px' }}>
        <h2 className="mb-4 text-center">🔐 Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Benutzername</label>
            <input
              type="text"
              className="form-control"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Passwort</label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary w-100">Anmelden</button>
          {message && <div className="alert alert-danger mt-3">{message}</div>}
        </form>
      </div>
    </div>
  );
}
