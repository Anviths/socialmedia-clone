import React, { useState } from 'react';
import axios from 'axios';
import './SignupForm.css';

const SignupForm = () => {
  const [formData, setFormData] = useState({
    userName: '',
    email: '',
    phone: '',
    password: '',
    fullname: '',
    date_of_birth: '',
    bio: '',
    profile: null,
    cover: null
  });

  const handleChange = (e) => {
    let { name, value}=e.target;
    setFormData({ ...formData, [name]:value });
  };

  const handleFileChange = (e) => {
    let { name, files}=e.target;
    setFormData({ ...formData, [name]:files[0] });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formDataToSend = new FormData();
    formDataToSend.append('userData', JSON.stringify(formData));
    formDataToSend.append('profile', formData.profile);
    formDataToSend.append('cover', formData.cover);

    try {
      const response = await axios.post('http://localhost:8080/facebook', formDataToSend);
      console.log(response.data); // Handle success response
    } catch (error) {
      console.error('Error:', error); // Handle error
    }
  };

  return (
    <form className="signup-form" onSubmit={handleSubmit}>
      <input type="text" name="userName" placeholder="Username" value={formData.username} onChange={handleChange} />
      <input type="tel" name="phone" placeholder="Phone" value={formData.phone} onChange={handleChange} />
      <input type="text" name="fullname" placeholder="Fullname" value={formData.fullname} onChange={handleChange} />
      <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} />
      <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
      <input type="date" name="date_of_birth" placeholder="Date of Birth" value={formData.date_of_birth} onChange={handleChange} />
      <input type="text" name="bio" placeholder="Bio" value={formData.bio} onChange={handleChange} />
      <input type="file" name="profile" onChange={handleFileChange} />
      <input type="file" name="cover" onChange={handleFileChange} />
      <button type="submit">Sign Up</button>
    </form>
    
  );
};

export default SignupForm;
