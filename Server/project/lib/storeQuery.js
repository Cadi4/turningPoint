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
	//filter : 1. score / 2. countN / 3. reviewN
	if(kind != '' && style != ''){
		Store.find({
			$or: [	{'info.kind': kind.substr(0,5)}, 
				{'info.kind': kind.substr(5,5)}, 
				{'info.kind': kind.substr(10,5)}], 
			$or: [	{'info.style': style.substr(0,5)}, 
				{'info.style': style.substr(5,5)},
				{'info.style': style.substr(10,5)}], 
		}).sort({sortFlag: 1}).exec(callback);
	}
	else if(kind != ''){
		Store.find({
			$or: [	{'info.kind': kind.substr(0,5)}, 
				{'info.kind': kind.substr(5,5)},
				{'info.kind': kind.substr(10,5)}], 
		}).sort({sortFlag: 1}).exec(callback);
	}
	else if(style != ''){
		Store.find({
			$or: [	{'info.style': style.substr(0,5)}, 
				{'info.style': style.substr(5,5)},
				{'info.style': style.substr(10,5)}], 
		}).sort({sortFlag: 1}).exec(callback);
	}
	else{
		Store.find().sort({sortFlag: 1}).exec(callback);
	}
}

function getStoreByLocation(X, Y, callback){
	Store.find({ 
			'location.x1': {$lt: X}, 'location.x2': {$gt: X},
			'location.y1': {$lt: Y}, 'location.y2': {$gt: Y}
		}).exec(callback);
}
