const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');
const Teacher = require('../models/teacher'); 

exports.signup = async (req, res) => {
  const { email, password } = req.body;

  try {
    const existingTeacher = await Teacher.findOne({ where: { email } });
    if (existingTeacher) {
      return res.status(400).json({ error: 'Email already exists' });
    }

    // Hash the password
    const hashedPassword = await bcrypt.hash(password, 10);

    const newTeacher = await Teacher.create({
      email,
      password: hashedPassword
    });

    const payload = {
      id: newTeacher.id,
      role: "Teacher"
    };

    // Create and sign a JWT token
    const token = jwt.sign(payload, 'SomeSecretNotToShare', { expiresIn: '1h' });

    res.json({ token });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to signup' });
  }
};

exports.login = async (req, res) => {
  const { email, password } = req.body;

  try {
    const teacher = await Teacher.findOne({ where: { email } });

    // Check if the teacher exists
    if (!teacher) {
      return res.status(401).json({ error: 'Invalid email or password' });
    }

    const isPasswordValid = await bcrypt.compare(password, teacher.password);
    if (!isPasswordValid) {
      return res.status(401).json({ error: 'Invalid email or password' });
    }

    const payload = {
      id: teacher.id,
      role: "Teacher"
    };

    // Sign the JWT token with the payload
    const token = jwt.sign(payload, 'SomeSecretNotToShare', { expiresIn: '23h' });

    res.json({ token });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to login' });
  }
};