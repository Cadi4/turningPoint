var async = require('async');
var mongoose = require('mongoose');
require(process.cwd() + '/lib/connections');
var Store = mongoose.model('Store');

var data = {
	stores: 
	[
		{
			id: "GS1",
			name: ["media", "미디어포레", "미디어포래", "mediaf"],
			location: {
				x1: 252,
				x2: 288,
				y1: 342,
				y2: 422
			},
			info: {
				goods: "selling goods",
				kind: "girlcrush",
				style: "street",
				price: "10000",
				card: true,
				refund: true,
				exchange: true,
				fitting: true,
				open: 900,
				close: 2400,
				holiday: ["sat", "sun"],
				etc : ''
			}
		},
		{
			id: "DR11",
			name: ["11번출구", "11번", "door11", "11gate", "gate11"],
			location: {
				x1: 890,
				x2: 909,
				y1: 480,
				y2: 500
			}
		},
		{
			id: "DR7",
			name: ["7번출구", "7번", "door7", "7gate", "gate7"],
			location: {
				x1: 74,
				x2: 89,
				y1: 255,
				y2: 284
			}
		},
		{
			id: "DR12",
			name: ["12번출구", "12번", "door12", "12gate", "gate12"],
			location: {
				x1: 555,
				x2: 571,
				y1: 904,
				y2: 925
			}
		},
		{
			id: "DR99",
			name: ["이즈타워", "이즈타워출구", "istower"],
			location: {
				x1: 721,
				x2: 776,
				y1: 550,
				y2: 582
			}
		}
	],
	users: 
	[
		{
			userKey: 0,
			userEmail: "abc@naver.com",
			userName: "옥이",
			userStyle: ["basic", "lovely", "compus"],
			favoriteStore: ["DR99", "DR12"],
			favoriteReviewr: [1, 3],
			historyStore: ["DR99", "DR12", "DR7", "GS1"],
			writenReview: [2, 8, 10, 12, 30],
			loveReview: [40, 50]
		}
	]
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

async.series([
	deleteStores,
	addStores
], function(error, results){
	if(error){
		console.error('Error: ' + error);
	}
	mongoose.connection.close();
	console.log('Done!');
});
