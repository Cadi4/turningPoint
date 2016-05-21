var http = require('http');
require(process.cwd() + '/lib/connections');
var storeService = require('./lib/stores');
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
	else if(_url = /^\/stores\/(\S+)\/(\S+)\/(\S+)$/i.exec(req.url)){
		console.log('Stores');
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
	else{
		res.writeHead(200);
		res.end('static file maybe');
	}
}).listen(1337);

console.log('Server running at http://52.196.54.163:1337');
