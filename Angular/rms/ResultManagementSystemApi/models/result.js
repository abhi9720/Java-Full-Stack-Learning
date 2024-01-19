// models/result.js

const { DataTypes, Sequelize } = require('sequelize');
const sequelize = require('../config/database');
const moment = require('moment');

const Result = sequelize.define('Result', {
  name: {
    type: DataTypes.STRING,
    allowNull: false
  },
  rollNo: {
    type: DataTypes.INTEGER,
    allowNull: false,
    unique: true
  },
  score: {
    type: DataTypes.INTEGER,
    allowNull: false
  },
  dob: {
    type: Sequelize.DATEONLY,
    allowNull: false
    }
});

module.exports = Result;
