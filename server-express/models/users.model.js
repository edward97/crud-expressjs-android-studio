const mongoose = require('mongoose')

const UserSchema = mongoose.Schema({
  email: String,
  username: String,
  password: String,
}, {
    timestamps: true
})

module.exports = mongoose.model('Users', UserSchema)
