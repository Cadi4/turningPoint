var mongoose = require('mongoose');
var Store = mongoose.model('Store');

exports.getStores = getStores;
exports.getStore = getStore;
exports.getLocation = getLocation;

function getStores(callback){
	Store.find().sort('name.last').exec(callback);
}

function getStore(storeName, callback){
	Store.findOne({ name: storeName }).exec(callback);	
}

function getLocation(storeName, callback){
	console.log("hi   " + storeName);
	Store.findOne({ name: storeName }, { location: true}).exec(callback);	
}
