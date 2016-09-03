var mongoose = require('mongoose');
require(process.cwd().substr(0,-5) + '../lib/connections');
var Store = mongoose.model('Store');
var storeData = require('./storeData');
var userData = require('./userData');
var reviewData =require('./reviewData');

var data = {
	store: storeData.storeDataList(),
	user: userData.userDataList(),
	reviewData: reviewData.reviewDataList()
};
console.log(data);
