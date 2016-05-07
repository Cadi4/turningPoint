var mongoose = require('mongoose');
var dbUrl = 'mongodb://admin:admin11@ds013202.mlab.com:13202/hrappcadi';

mongoose.connect(dbUrl);

process.on('SIGINT', function() {
	mongoose.connection.close(function(){
		console.log('Mongoose default connection disconnected');
		process.exit(0)
	});
});

require('../models/store');

