import React, { useState } from 'react';
import axios from 'axios';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('CLIENT');

  const registerUser = async () => {
    try {
      await axios.post('http://localhost:8080/auth/register', {
        username,
        password,
        role,
      });
      alert('User registered successfully!');
    } catch (error) {
      console.error('Error registering user:', error);
      alert('Registration failed!');
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <input placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
      <select onChange={(e) => setRole(e.target.value)}>
        <option value="CLIENT">Client</option>
        <option value="CASHIER">Cashier</option>
        <option value="ADMIN">Admin</option>
      </select>
      <button onClick={registerUser}>Register</button>
    </div>
  );
}

export default Register;
