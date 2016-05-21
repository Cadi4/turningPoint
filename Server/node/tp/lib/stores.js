var mongoose = require('mongoose');
var Store = mongoose.model('Store');

exports.getStores = getStores;
exports.getStore = getStore;
exports.getLocation = getLocation;
exports.getStoreById = getStoreById;
exports.getStoreByLocation = getStoreByLocation;
exports.getStoreByFilter = getStoreByFilter;

function getStores(callback){
	Store.find().sort('name.last').exec(callback);
}

function getStore(storeName, callback){
	Store.find({ name: storeName }).exec(callback);	
}

function getLocation(storeName, callback){
	Store.findOne({ name: storeName }, { location: true}).exec(callback);	
}

function getStoreById(storeId, callback){
	Store.find({ id: storeId }).exec(callback);
}

function getStoreByFilter(kind, style, sortFlag, callback){
	Store.find({ 
			'info.kind': kind,
			'info.style': style
		}).sort({sortFlag: 1}).exec(callback);
}

function getStoreByLocation(X, Y, callback){
	Store.find({ 
			'location.x1': {$lt: X}, 'location.x2': {$gt: X},
			'location.y1': {$lt: Y}, 'location.y2': {$gt: Y}
		}).exec(callback);
}
