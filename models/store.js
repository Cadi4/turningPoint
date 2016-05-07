var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var StoreSchema = new Schema({
	id: {
		type: String,
		required: true,
		unique: true
	},
	name: {
		type: String,
		required: true
	},
	location: {
		x: {
			type: Number,
			required: true
		},
		y: {
			type: Number,
			required: true
		}
	},
	info: {
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
		image: {
			type: String,
			default: 'images/user.png'
		}
	}
});

module.exprots = mongoose.model('Store', StoreSchema);
