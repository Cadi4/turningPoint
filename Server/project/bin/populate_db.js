var async = require('async');
var mongoose = require('mongoose');
require(process.cwd() + '/lib/connections');
var Store = mongoose.model('Store');
var User = mongoose.model('User');
var Review = mongoose.model('Review');
var storeData = require('./storeData');
var userData = require('./userData');
var reviewData = require('./reviewData');

var data = {
	stores: storeData.storeDataList(),
	users: userData.userDataList(),
	reviews: reviewData.reviewDataList()
};

var deleteStores = function(callback){
	console.info('Deleting stores');
	Store.remove({}, function(error, response){
		if(error){
			console.error('Error deleting stores: ' + error);
		}
		console.info('Done deleting stores');
		callback();
	});
};

var addStores = function(callback){
	console.info('Adding stores');
	Store.create(data.stores, function(error){
		if(error){
			console.error('Error adding stores: ' + error);
		}
		console.info('Done adding stores');
		callback();
	});
};

var deleteUsers = function(callback){
	console.info('Deleting users');
	User.remove({}, function(error, response){
		if(error){
			console.error('Error deleting users: ' + error);
		}
		console.info('Done deleting users');
		callback();
	});
};

var addUsers = function(callback){
	console.info('Adding users');
	User.create(data.users, function(error){
		if(error){
			console.error('Error adding users: ' + error);
		}
		console.info('Done adding users');
		callback();
	});
};

var deleteReviews = function(callback){
	console.info('Deleting reviews');
	Review.remove({}, function(error, response){
		if(error){
			console.error('Error deleting reviews: ' + error);
		}
		console.info('Done deleting reviews');
		callback();
	});
};

var addReviews = function(callback){
	console.info('Adding reviews');
	Review.create(data.reviews, function(error){
		if(error){
			console.error('Error adding reviews: ' + error);
		}
		console.info('Done adding reviews');
		callback();
	});
};



async.series([
	deleteStores,
	addStores,
	deleteUsers,
	addUsers,
	deleteReviews,
	addReviews
	], function(error, results){
		if(error){
			console.error('Error: ' + error);
	}
	mongoose.connection.close();
	console.log('Done!');
});
