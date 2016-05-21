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
		goods: {
			type: String,
		},
		kind: {
			type: String,
		},
		style: {
			type: String,
		},
		price: {
			type: String,
		},
		card: {
			type: Boolean,
		},
		refund: {
			type: Boolean,
		},
		exchange: {
			type: Boolean,
		},
		fitting: {
			type: Boolean,
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
			default: 'images/user.png'
		},
		score: {
			type: Number,
		}
	}
});

module.exprots = mongoose.model('Store', StoreSchema);
