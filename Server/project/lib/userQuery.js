var mongoose = require('mongoose');
var User = mongoose.model('User');

exports.getUsers = getUsers;
exports.getUserById = getUserById;
exports.getUserByNickname = getUserByNickname;
exports.getUserByEmail = getUserByEmail;

function getUsers(callback){
	User.find().sort('userId').exec(callback);
}

function getUserById(calledId, callback){
	User.find({ userId: calledId }).exec(callback);
}

function getUserByNickname(calledNick, callback){
	User.find({ nickname: calledNick }).exec(callback);
}

function getUserByEmail(calledEmail, callback){
	User.find({ email: calledEmail }).exec(callback);
}
