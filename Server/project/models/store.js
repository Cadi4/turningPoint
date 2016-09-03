var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var StoreSchema = new Schema({
	id: {
		type: String,
		required: true,
		unique: true
	},
	name: {
		type: Array,
		required: true
	},
	location: {
		x1: {
			type: Number,
			required: true
		},
		x2: {
			type: Number,
			required: true
		},
		y1: {
			type: Number,
			required: true
		},
		y2: {
			type: Number,
			required: true
		}
	},
	info: {
		kind: {
			type: Array,
		},
		style: {
			type: Array,
		},
		price: {
			type: String,
		},
		card: {
			type: Number,
		},
		cash: {
			type: Number,
		},
		refund: {
			type: Number,
		},
		exchange: {
			type: Number,
		},
		fitting: {
			type: Number,
		},
		open: {
			type: Number,
		},
		close: {
			type: Number,
		},
		holiday: {
			type: Array,
		},
		image: {
			type: String,
			default: 'images/store/default.png'
		},
		score: {
			type: Number,
		},
		countN: {
			type: Number,
		}
	}
});

module.exports = mongoose.model('Store', StoreSchema);
