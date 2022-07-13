//entry point of the backend

const connectToMonogo = require('./db');
const express = require('express');

connectToMonogo();
const app = express()
const port = 3000

app.get('/', (req, res) => {
  res.send('aa gaye maja')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})