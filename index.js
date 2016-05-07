var http = require('http');
require(process.cwd() + '/lib/connections');
var storeService = require('./lib/stores');
var responder = require('./lib/responseGenerator');
var staticFile = responder.staticFile('./public');

http.createServer(function (req, res) {
	var _url;

	req.method = req.method.toUpperCase();
	console.log(req.method + ' ' + req.url);

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
		console.log('st');
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
	else if(_url = /^\/location\/(\S+)$/i.exec(req.url)){
		console.log('lo');
		storeService.getLocation(_url[1], function(error, data){
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
console.log('Clothes Up! Server running http://192.168.187.128:1337/');
