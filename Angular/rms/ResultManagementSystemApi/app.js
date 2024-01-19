require('dotenv').config();

const express =  require('express');
const app =  express();
const morgan = require('morgan')
const cors = require('cors')
const sequelize = require('./config/database');

const studentRoutes = require("./routes/studentRoutes");
const teacherRoutes = require('./routes/teacherRoutes');
const resultRoutes   =  require('./routes/resultRoutes')

app.use(
    cors({
        origin: "*",
        methods: ["GET", "POST", "DELETE", "UPDATE", "PUT", "PATCH"],
        allowedHeaders: "*",
        credentials: true,
    })
);


app.use(morgan("dev"));

// Connect to the MySQL database
sequelize.sync().then(() => {
    console.log('Database synced');
  }).catch((error) => {
    console.log('Error syncing database:', error);
  });   
 


app.use(express.json({ extended: false })); // to get body data similar to body parser

// Handling Routes 
app.use("/students", studentRoutes);
app.use('/teachers', teacherRoutes);
app.use('/results',resultRoutes)



const PORT  =  3000;
app.listen(PORT, ()=>{
    console.log("Server Running on PORT : "+PORT)
})