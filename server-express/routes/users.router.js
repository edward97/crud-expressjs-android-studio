const express = require('express')
const router = express.Router()
const users = require('../controllers/users.controller')

/* GET users listing. */
// router.get('/', (req, res, next) => {
//   res.render('users/index', {
//     title: 'Users'
//   })
// })
// router.get('/', (req, res, next) => {
//   res.json({
//     "message": "this is a json."
//   })
// })

router.get('/', users.listUsers)
router.post('/', users.createUser)
router.get('/:userId', users.editUser)
router.put('/:userId', users.updateUser)
router.delete('/:userId', users.deleteUser)

module.exports = router
