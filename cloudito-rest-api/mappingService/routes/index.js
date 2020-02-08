var express = require('express');
var router = express.Router();
var map = require('../map.json');
var fs = require('fs');
var service = require("../service/mapService");

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });

})
router.get('/map/nodes',function(req,res,next) {
  res.send(service.getNodes());
})
router.get('/map/course/:A/:B',function(req,res,next) {
  var A = req.param("A",0);
  var B = req.param("B",0);
  res.send(service.getCourse(A,B));
})

router.get('/map/course/:customerid',function(req,res,next) {
  res.send({});
})

router.get('/map/stores',function(req,res,next) {
    res.send(service.getStores());
})
router.get('/mapRaw/',function(req,res,next) {
  res.send(service.getCourse2());
})

module.exports = router;
