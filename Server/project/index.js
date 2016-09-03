var http = require('http');
require(process.cwd() + '/lib/connections');
var storeService = require('./lib/storeQuery');
var userService = require('./lib/userQuery');
var reviewService = require('./lib/reviewQuery');
var responder = require('./lib/responseGenerator');
var staticFile = responder.staticFile('./public');
var urlEncoder = require('urlencode');

http.createServer(function (req, res) {
	var _url;

	req.method = req.method.toUpperCase();
	console.log(req.method + ' ' + urlEncoder.decode(req.url));

	if(req.method !== 'GET'){
		res.writeHead(501, {'Content-Type': 'text/plain'});
		return res.end(req.method + ' is not implemented by this server.');
	}
	else if(_url = /^\/stores$/i.exec(req.url)){
		storeService.getStores(function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/stores\/(\S+)$/i.exec(req.url)){
		console.log('byName');
		_url[1] = urlEncoder.decode(_url[1]);
		storeService.getStore(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/storesId\/(\S+)$/i.exec(req.url)){
		console.log('byId');
		storeService.getStoreById(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/storesLocation\/(\d+)\/(\d+)$/i.exec(req.url)){
		console.log('byLocation');
		storeService.getStoreByLocation(_url[1], _url[2], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/storesFilter\/(\S+)\/(\S+)\/(\S+)$/i.exec(req.url)){
		console.log('StoresFilter');
		storeService.getStoreByFilter(_url[1], _url[2],_url[3],  function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/users$/i.exec(req.url)){
		console.log('Users');
		userService.getUsers(function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/usersById\/(\d+)$/i.exec(req.url)){
		console.log('usersById');
		userService.getUserById(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/usersByNickname\/(\S+)$/i.exec(req.url)){
		console.log('users by nickname');
		_url[1] = urlEncoder.decode(_url[1]);
		userService.getUserByNickname(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/usersByEmail\/(\S+)$/i.exec(req.url)){
		console.log('users by email');
		_url[1] = urlEncoder.decode(_url[1]);
		userService.getUserByEmail(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/reviews$/i.exec(req.url)){
		console.log('all reviews');
		reviewService.getReviews(function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/reviewsByStore\/(\S+)$/i.exec(req.url)){
		console.log('review by store');
		_url[1] = urlEncoder.decode(_url[1]);
		reviewService.getReviewByStore(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/reviewsByUserId\/(\d+)$/i.exec(req.url)){
		console.log('review by user');
		reviewService.getReviewByUserId(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/reviewsByReviewId\/(\d+)$/i.exec(req.url)){
		console.log('review by review id');
		reviewService.getReviewByReviewId(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else if(_url = /^\/reviewCount\/(\S+)$/i.exec(req.url)){
		console.log('count of review by store id');
		reviewService.getReviewCountByStore(_url[1], function(error, data){
			if(error){
				return responder.send500(error, res);
			}
			if(!data){
				return responder.send404(res);
			}
			return responder.sendJson(data, res);
		});
	}
	else{
		res.writeHead(200);
		res.end('static file maybe');
	}
}).listen(1337);

console.log('Server running at http://52.196.54.163:1337');
