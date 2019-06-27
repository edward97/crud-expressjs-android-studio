const userModel = require('../models/users.model')

module.exports = {
    listUsers: (req, res) => {
        userModel.find()
        .then(users => {
            res.json({ result: users })
            // res.send(users)
        }).catch(err => {
            res.status(500).send({
                message: err.message || "Error when readint user."
            })
        })

        // res.render('users/index', {
        //     title: 'Users'
        // })
    },
    createUser: (req, res) => {
        // if(! req.body.content) {
        //     return res.status(400).send({
        //         message: "Field cannot be empty"
        //     })
        // }

        // create new user
        const user = new userModel({
            email: req.body.email,
            username: req.body.username,
            password: req.body.password
        })

        user.save()
        .then(data => {
            res.send(data)
        }).catch(err => {
            res.status(500).send({
                message: err.message || "Error when create new user."
            })
        })
    },
    editUser: (req, res) => {
        userModel.findById((req.params.userId))
        .then(user => {
            if (! user) {
                return res.status(404).send({
                    message: 'User not found with ID: ' +  req.params.userId
                })
            }
            res.send(user);
        }).catch(err => {
            if(err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: 'User not found with ID: ' +  req.params.userId
                })
            }
            return res.status(500).send({
                message: 'Error read user with ID: ' +  req.params.userId
            })
        })
    },
    updateUser: (req, res) => {
        userModel.findByIdAndUpdate(req.params.userId, {
            email: req.body.email,
            username: req.body.username,
            password: req.body.password
        }, { new: true })
        .then(user => {
            if (! user) {
                return res.status(404).send({
                    message: 'User not found with ID: ' + req.params.userId
                })
            }
            res.send(user)
        }).catch(err => {
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: 'User not found with ID: ' + req.params.userId
                })
            }
            return res.status(500).send({
                message: 'Error when updating user with ID: ' + req.params.userId
            })
        })
    },
    deleteUser: (req, res) => {
        userModel.findByIdAndRemove(req.params.userId)
        .then(user => {
            if (! user) {
                return res.status(404).send({
                    message: 'User not found with ID: ' + req.params.userId
                })
            }
            res.send({
                message: 'Delete user successfully.'
            })
        }).catch(err => {
            if (err.kind === 'ObjectId' || err.name === 'NotFound') {
                return res.status(404).send({
                    message: 'User not found with ID: ' + req.params.userId
                })
            }
            return res.status(500).send({
                message: 'Error when deleteting data with ID: ' + req.params.userId
            })
        })
    }
}