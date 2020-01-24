var express = require('express');
var router = express.Router();
var map = require('../map.json');
var fs = require('fs');
const { createCanvas, loadImage } = require('canvas')
const canvas = createCanvas(2000, 2000)
const ctx = canvas.getContext('2d')
const decalageX = 0;
const decalageY = 0;
const Graph =  require("@vila91/graph")

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });

})
router.get('/map/nodes',function(req,res,next) {
  var mapObj = {};
  var text = ctx.measureText('Awesome!')
  ctx.strokeStyle = 'rgba(0,0,0,5)';
  //ctx.beginPath();
  /*for(var j=0;j<map.pois.polygons.length;j++) {
    ctx.beginPath();
    var array = map.pois.polygons[j].points.split(',');
    if(/^\d+$/.test(map.pois.polygons[j].poiID) ) {
      for (var i = 0; i < array.length; i++) {
        var first = array[i].split(' ')[0];
        var second = array[i].split(' ')[1];
        ctx.lineTo(first, second);
        //console.log("point :" + first + " ; " + second);
      }
    }
    ctx.stroke();
  }*/

  //ctx.strokeStyle = 'rgba(100,255,0,5)';
  //ctx.beginPath();
  mapObj.nodes = Array();
  for(var j=0 ; j<map.routing.nodes.length;j++){
    if(map.routing.nodes[j].f!=0) {
      ctx.fillRect(map.routing.nodes[j].x + decalageX, map.routing.nodes[j].y + decalageY, 2, 2);
      mapObj.nodes.push({id: j, x: map.routing.nodes[j].x + decalageX, y: map.routing.nodes[j].y + decalageY})
    }
  }
  //test de parcours entre le point 4 et le point 49
  /*var g = new Graph()
  ctx.strokeStyle = 'rgba(255,0,0,5)';
  mapObj.chemins=Array();
  mapObj.map= Array();
  for(var j=0;j<map.routing.edges.length;j++){
    ctx.beginPath();
    if(map.routing.nodes[map.routing.edges[j].s].f !=0 && map.routing.nodes[map.routing.edges[j].e].f != 0) {
      ctx.moveTo(map.routing.nodes[map.routing.edges[j].s].x+decalageX, map.routing.nodes[map.routing.edges[j].s].y+decalageY);
      ctx.lineTo(map.routing.nodes[map.routing.edges[j].e].x+decalageX, map.routing.nodes[map.routing.edges[j].e].y+decalageY);
      mapObj.chemins.push( {s: map.routing.edges[j].s, e: map.routing.edges[j].e, x1:map.routing.nodes[map.routing.edges[j].s].x+decalageX, y1:map.routing.nodes[map.routing.edges[j].s].y+decalageY, x2 : map.routing.nodes[map.routing.edges[j].e].x+decalageX, y2 : map.routing.nodes[map.routing.edges[j].e].y+decalageY} );
      g.connect(map.routing.edges[j].s,map.routing.edges[j].e);
      //console.log(j);
      //mapObj.map.push({id:map.routing.edges[j].s,liste:findAutour(map.routing.edges[j].s,mapObj)});
    }
    ctx.stroke();
  }
  //for each point


  var cheminFinal = Array();
  ctx.beginPath();
  //var cheminCalcul = route.path('1215', '1710');
  //console.log(cheminCalcul);
  //var cheminCalcul = algoChemin(cheminFinal,3085,2530, mapObj);


  ctx.strokeStyle = 'rgba(0,255,0,5)';
  var liste  = g.route(3085,1710);
  var cheminCalcul = liste.nodes;
  //console.log(cheminCalcul);
  ctx.beginPath();
  for (var j = 0 ; j< cheminCalcul.length-1 ; j++){
    ctx.moveTo(map.routing.nodes[cheminCalcul[j]].x+decalageX, map.routing.nodes[cheminCalcul[j]].y+decalageY);
    //console.log(map.routing.nodes[cheminCalcul[j]].x+decalageX+" : "+map.routing.nodes[cheminCalcul[j]].y+decalageY)
    ctx.lineTo(map.routing.nodes[cheminCalcul[j+1]].x+decalageX, map.routing.nodes[cheminCalcul[j+1]].y+decalageY);
  }*/

  //ctx.stroke();
  //res.setHeader('Content-Type', 'image/png');
  //canvas.pngStream().pipe(res);
  res.send(mapObj);
})
router.get('/map/course/:A/:B',function(req,res,next) {
  var mapObj = {};
  var A = req.param("A",0);
  var B = req.param("B",0);
  var text = ctx.measureText('Awesome!')
  ctx.strokeStyle = 'rgba(0,0,0,5)';
  //ctx.beginPath();
  /*for(var j=0;j<map.pois.polygons.length;j++) {
    ctx.beginPath();
    var array = map.pois.polygons[j].points.split(',');
    if(/^\d+$/.test(map.pois.polygons[j].poiID) ) {
      for (var i = 0; i < array.length; i++) {
        var first = array[i].split(' ')[0];
        var second = array[i].split(' ')[1];
        ctx.lineTo(first, second);
        //console.log("point :" + first + " ; " + second);
      }
    }
    ctx.stroke();
}*/

//ctx.strokeStyle = 'rgba(100,255,0,5)';
//ctx.beginPath();
mapObj.nodes = Array();
for(var j=0 ; j<map.routing.nodes.length;j++){
  if(map.routing.nodes[j].f!=0) {
    mapObj.nodes.push({id: j, x: map.routing.nodes[j].x + decalageX, y: map.routing.nodes[j].y + decalageY})
  }
}
//test de parcours entre le point 4 et le point 49
var g = new Graph()
ctx.strokeStyle = 'rgba(255,0,0,5)';
mapObj.chemins=Array();
mapObj.map= Array();
for(var j=0;j<map.routing.edges.length;j++){
  ctx.beginPath();
  if(map.routing.nodes[map.routing.edges[j].s].f !=0 && map.routing.nodes[map.routing.edges[j].e].f != 0) {
    //mapObj.chemins.push( {s: map.routing.edges[j].s, e: map.routing.edges[j].e, x1:map.routing.nodes[map.routing.edges[j].s].x+decalageX, y1:map.routing.nodes[map.routing.edges[j].s].y+decalageY, x2 : map.routing.nodes[map.routing.edges[j].e].x+decalageX, y2 : map.routing.nodes[map.routing.edges[j].e].y+decalageY} );
    g.connect(map.routing.edges[j].s,map.routing.edges[j].e);
  }
  ctx.stroke();
}
//for each point


var cheminFinal = Array();
//var liste  = g.route(3085,1710);
var liste  = g.route(Number(A),Number(B));
var cheminCalcul = liste.nodes;
ctx.beginPath();
res.send(cheminCalcul);
})
router.get('/map/stores',function(req,res,next) {
    var mapObj = Array();
    var A = req.param("A",0);
    var B = req.param("B",0);
    var text = ctx.measureText('Awesome!')
    ctx.strokeStyle = 'rgba(0,0,0,5)';
    //ctx.beginPath();
    for(var j=0;j<map.pois.polygons.length;j++) {
      ctx.beginPath();
      var array = map.pois.polygons[j].points.split(',');
      if(/^\d+$/.test(map.pois.polygons[j].poiID) ) {
          var array2 = Array();
          for (var i = 0; i < array.length; i++) {
              var first = array[i].split(' ')[0];
              var second = array[i].split(' ')[1];
              array2.push( [first,second]);
              ctx.lineTo(first, second);
              console.log("point :" + first + " ; " + second);
          }
          mapObj.push(array2);
      }
      ctx.stroke();
  }
    res.send(mapObj);
})
router.get('/mapRaw/',function(req,res,next) {
  res.send(map);
})

algoChemin = function(cheminFinal,s,e,mapObj){
  var lastPoint = s;
  cheminFinal.push(lastPoint);
  if(routeExist(lastPoint,e,mapObj)){
    return [lastPoint,e];
  }
  else {
    //trouver les points autours de lastpoint
    var nearest = findNearest(findAutour(lastPoint,mapObj),e,mapObj).sort(function(a,b){
      return a.d-b.d;
    })
    var goodpoint = null;
    for(var j = 0 ; j<nearest.length;j++) {
      if(!cheminFinal.includes(nearest[j])){
        goodpoint = nearest[j].id;
        break;
      }
    }
    cheminFinal.concat(algoChemin(cheminFinal,goodpoint,e,mapObj));
    /*
    findAutour(lastPoint,mapObj).forEach(val => {
      if(!cheminFinal.includes(val)){
        cheminFinal.concat(algoChemin(cheminFinal,val,e,mapObj));
      }
      return cheminFinal;
    })*/
    return cheminFinal;
  }
}
findAutour = function(p,mapObj){
  var liste = Array();
  for(var j =0;j<mapObj.chemins.length;j++){
    if(routeExist(p,mapObj.chemins[j].e,mapObj)){
      if(!liste.includes([mapObj.chemins[j].e.toString,1]))
        liste.push([mapObj.chemins[j].e.toString(),1]);
    }
  }
  //console.log(liste);
  return liste;
}
findNearest = function (liste,e, mapObj){
  nearest = Array();
  minDistance = 10000;
  liste.forEach(val => {
    var p1 = mapObj.nodes[e];
    var p2 = mapObj.nodes[val];
    distance = Math.sqrt((Math.pow(p1.x-p2.x,2))+(Math.pow(p1.y-p2.y,2)))
    //distance = Math.pow(4);
    nearest.push({d:distance,id: val});
  })
  return nearest;
}

routeExist = function(s,e,mapObj){
  for(var j=0;j<mapObj.chemins.length;j++){
    if(mapObj.chemins[j].s == s && mapObj.chemins[j].e == e)
      return true;
  }
  return false;
}




module.exports = router;
