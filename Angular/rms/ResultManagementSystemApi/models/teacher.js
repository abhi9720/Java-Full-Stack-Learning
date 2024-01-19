const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');


const Teacher = sequelize.define('Teacher', {
  email: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true
  },
  password: {
    type: DataTypes.STRING,
    allowNull: false
  }
}, {
  // Other model options
  tableName: 'teachers' // Specify the table name explicitly if needed
});

module.exports = Teacher;
