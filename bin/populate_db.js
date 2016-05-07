var async = require('async');
var mongoose = require('mongoose');
require(process.cwd() + '/lib/connections');
var Store = mongoose.model('Store');

var data = {
	stores: [{
			id: 'GS1',
			name: 'media',
			location: {
				x: 270,
				y: 377
			},
			info: {
				kind: 'girlcrush',
				style: 'street',
				price: '10000',
				card: true,
				refund: true,
				exchange: true,
				fitting: true,
			}
		},
		{
			id: 'DR11',
			name: '11번출구',
			location: {
				x: 904,
				y: 490
			}
		},
		{
			id: 'DR7',
			name: '7번출구',
			location: {
				x: 81,
				y: 272
			}
		},
		{
			id: 'DR12',
			name: '12번출구',
			location: {
				x: 56,
				y: 916
			}
		},
		{
			id: 'DR99',
			name: '이즈타워',
			location: {
				x: 755,
				y: 562
			}
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
