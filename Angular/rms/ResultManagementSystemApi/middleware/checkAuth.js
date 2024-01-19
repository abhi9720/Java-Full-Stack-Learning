const jwt = require('jsonwebtoken');

const checkAuth = (req, res, next) => {
  try {
    // Extract the token from the request headers
    const token = req.headers.authorization.split(' ')[1];

    // Verify the token
    const decodedToken = jwt.verify(token, 'SomeSecretNotToShare');

    // Attach the decoded token to the request object
    req.userData = decodedToken;

    // Proceed to the next middleware or route handler
    next();
  } catch (error) {
    return res.status(401).json({ error: 'Authentication failed' });
  }
};

module.exports = checkAuth;
