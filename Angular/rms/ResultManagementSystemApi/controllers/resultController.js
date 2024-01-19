
const Result = require('../models/result');

// Create a new result
exports.createResult = async (req, res) => {
  try {
    const { dob, name, rollNo, score } = req.body;

    // Check if rollNo already exists
    const existingResult = await Result.findOne({ where: { rollNo } });
    if (existingResult) {
      return res.status(400).json({ error: 'RollNo already exists' });
    }

    const newResult = await Result.create({
      dob,
      name,
      rollNo,
      score
    });

    res.status(201).json(newResult);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to create result' });
  }
};

// Get all results
exports.getAllResults = async (req, res) => {
  try {
    const results = await Result.findAll();
    res.json(results);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to fetch results' });
  }
};

// Get a single result by ID
exports.getResultById = async (req, res) => {
  try {
    const rollNo = req.params.id;
    const result = await Result.findOne({ where: { rollNo } });
    if (result) {
      res.json(result);
    } else {
      res.status(404).json({ error: 'Result not found' });
    }
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to fetch result' });
  }
};

// Update a result by ID
exports.updateResultById = async (req, res) => {
  try {
    const rollNo = req.params.id;
    const updatedResult = await Result.findOne({ where: { rollNo } });

    if (!updatedResult) {
      return res.status(404).json({ error: 'Result not found' });
    }

    // Check if the updated roll number already exists
    const existingResult = await Result.findOne({ where: { rollNo: req.body.rollNo } });
    if (existingResult && existingResult.id !== updatedResult.id) {
      return res.status(400).json({ error: 'Duplicate roll number' });
    }

    await updatedResult.update({
      dob: req.body.dob,
      name: req.body.name,
      rollNo: req.body.rollNo,
      score: req.body.score
    });

    res.json(updatedResult);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to update result' });
  }
};

// Delete a result by ID
exports.deleteResultById = async (req, res) => {
  try {
    const rollNo = req.params.id;

    const deletedResult = await Result.destroy({ where: { rollNo } });
    if (deletedResult) {
      res.json({ message: 'Result deleted successfully' });
    } else {
      res.status(404).json({ error: 'Result not found' });
    }
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: 'Failed to delete result' });
  }
};

