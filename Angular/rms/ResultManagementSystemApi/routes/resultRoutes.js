
const express = require('express');
const router = express.Router();
const resultController =  require('../controllers/resultController');
const checkAuth = require('../middleware/checkAuth');


// Create a new result
router.post('/', checkAuth, resultController.createResult);

// Get all results
router.get('/',checkAuth, resultController.getAllResults);

// Get a single result by ID
router.get('/:id',checkAuth, resultController.getResultById);

// Update a result by ID
router.put('/:id',checkAuth, resultController.updateResultById);

// Delete a result by ID
router.delete('/:id',checkAuth, resultController.deleteResultById);


module.exports = router;
