var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var ReviewSchema = new Schema({
	reviewId: {
		type: Number,
		required: true
	},	
	userId: {
		type: Number,
		required: true,
	},
	id: {
		type: String,
	},
	nickname: {
		type: String,
	},
	grade: {
		type: Number,
	},
	style: {
		type: Array
	},
	photo: {
		type: String
	},
	text: {
		type: String
	},
	regiter_time: {
		type: Date
	}
});

module.exports = mongoose.model('Review', ReviewSchema);
