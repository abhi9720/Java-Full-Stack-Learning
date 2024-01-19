
const Result = require('../models/result');
const moment = require('moment');

// Search for a result by roll number and date of birth
exports.searchStudentResult = async (req, res) => {
  const { rollNo, dob } = req.query;
  console.log(rollNo,dob)
  try {
    const formattedDob = moment(dob).format('YYYY-MM-DD');
    console.log(rollNo,dob,formattedDob)
 
    const searchedResult = await Result.findOne({ where: { rollNo, dob: formattedDob } });
    if (searchedResult) {
      res.json(searchedResult);
    } else {
      res.status(404).json({ error: 'Result not found' });
    }
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to search for student' });
  }
};