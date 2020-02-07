var expect  = require('chai').expect;
var assert = require('chai').assert;
var request = require('request');
var service = require('../service/mapService')

it('map content', function(done) {
    expect(service.getNodes()).to.be.an("Array");
    done()
});
it('course content', function(done) {
    expect(service.getCourse(3085,1710)).to.deep.include({"id":2530,"location":{"x":1167.63,"y":975}});
    done()
});
it('find in map not null', function(done) {
    var mapObj = {nodes : [{id:1},{id:2}]}
    assert.notEqual(service.findInMap(1,mapObj),null);
    done()
});
it('find in map not null', function(done) {
    var mapObj = {nodes : [{id:1},{id:2}]}
    assert.equal(service.findInMap(3,mapObj),null);
    done()
});