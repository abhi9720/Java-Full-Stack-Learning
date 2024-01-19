const express = require('express');
const router = express.Router();
const teacherController = require('../controllers/teacherController');
const checkAuth = require('../middleware/checkAuth');

router.post('/login', teacherController.login);
router.post('/signup', teacherController.signup);






module.exports = router;
