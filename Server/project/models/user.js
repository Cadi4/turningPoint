var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var UserSchema = new Schema({
	userId: {
		type: Number,
		required: true,
		unique: true
	},
	email: {
		type: Array,
	},
	nickname: {
		type: String,
		required: true,
		unique: true
	},
	profile_img: {
		type: String,
		default: "/image/user/default.png"
	},
	style: {
		type: Array,
	},
	fav_store: {
		type: Array,
	},
	fav_reviewer: {
		type: Array,
	},
	visit_store: {
		type: Array,
	},
	good_review: {
		type: Array,
	}
});

module.exports = mongoose.model('User', UserSchema);
