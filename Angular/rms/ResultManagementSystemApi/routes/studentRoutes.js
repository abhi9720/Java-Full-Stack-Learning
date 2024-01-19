const express = require('express');
const router = express.Router();
const studentController = require('../controllers/studentController');


// Search for a result by roll number and date of birth
router.get('/search', studentController.searchStudentResult);

module.exports = router;
