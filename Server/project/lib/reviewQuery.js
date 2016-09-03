var mongoose = require('mongoose');
var Review = mongoose.model('Review');

exports.getReviews = getReviews;
exports.getReviewByStore = getReviewByStore;
exports.getReviewByUserId = getReviewByUserId;
exports.getReviewByReviewId = getReviewByReviewId;
exports.getReviewCountByStore = getReviewCountByStore;

function getReviews(callback){
	Review.find().sort('register_time').exec(callback);
}

function getReviewByStore(calledStoreId, callback){
	Review.find({ id: calledStoreId }).sort('register_time').exec(callback);
}

function getReviewByUserId(calledUserId, callback){
	Review.find({ userId: calledUserId }).sort('register_time').exec(callback);
}

function getReviewByReviewId(calledReviewId, callback){
	Review.find({ reviewId: calledReviewId }).sort('register_time').exec(callback);
}

function getReviewCountByStore(calledStoreId, callback){
	Review.find({ id: calledStoreId }).count().exec(callback);
}

