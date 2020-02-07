var map = require('../map.json');
const Graph =  require("@vila91/graph")

module.exports = {
    getNodes : function() {
        var mapObj = {};
        mapObj.nodes = Array();
        for(var j=0 ; j<map.routing.nodes.length;j++){
            if(map.routing.nodes[j].f!=0) {
                //ctx.fillRect(map.routing.nodes[j].x + decalageX, map.routing.nodes[j].y + decalageY, 2, 2);
                mapObj.nodes.push({id: j,location:{ x: map.routing.nodes[j].x, y: map.routing.nodes[j].y}})
            }
        }
        return mapObj.nodes;
    },
    getCourse : function( A, B) {
        var mapObj = {};

        mapObj.nodes = Array();
        for(var j=0 ; j<map.routing.nodes.length;j++){
            if(map.routing.nodes[j].f!=0) {
                mapObj.nodes.push({id: j,location:{ x: map.routing.nodes[j].x, y: map.routing.nodes[j].y}})
            }
        }
        var g = new Graph()
        mapObj.chemins=Array();
        mapObj.map= Array();
        for(var j=0;j<map.routing.edges.length;j++){
            if(map.routing.nodes[map.routing.edges[j].s].f !=0 && map.routing.nodes[map.routing.edges[j].e].f != 0) {
                g.connect(map.routing.edges[j].s,map.routing.edges[j].e);
            }
        }
        var cheminFinal = Array();
        var liste  = g.route(Number(A),Number(B));
        console.log(liste);
        var cheminCalcul = liste.nodes;
        cheminCalcul.forEach(val => {
            cheminFinal.push(this.findInMap(val,mapObj));
        })
        return cheminFinal;
    },
    getStores : function(){
        var mapObj = Array();
        for(var j=0;j<map.pois.polygons.length;j++) {
            var array = map.pois.polygons[j].points.split(',');
            if(/^\d+$/.test(map.pois.polygons[j].poiID) ) {
                var array2 = Array();
                for (var i = 0; i < array.length; i++) {
                    var first = array[i].split(' ')[0];
                    var second = array[i].split(' ')[1];
                    array2.push( [first,second]);
                }
                mapObj.push(array2);
            }
        }
        return mapObj;
    },
    findNearest : function (liste,e, mapObj){
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
    },
    findInMap : function(val, mapObj){
        for(var i = 0 ; i<mapObj.nodes.length;i++){
            if(mapObj.nodes[i].id==val)
                return mapObj.nodes[i];
        }
        return null;
    },
    routeExist : function(s,e,mapObj){
        for(var j=0;j<mapObj.chemins.length;j++){
            if(mapObj.chemins[j].s == s && mapObj.chemins[j].e == e)
                return true;
        }
        return false;
    }
}