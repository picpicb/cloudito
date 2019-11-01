package esipe.fr.geomarketing.controllers;


import esipe.fr.geomarketing.entities.GeoInfo;
import esipe.fr.geomarketing.services.GeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Geo")
public class GeoController {
    private GeoService cityService;

    @Autowired
    public GeoController(final GeoService cityService){
        this.cityService = cityService;
    }

    @RequestMapping(value = "/geo/{geoID}", method = RequestMethod.GET)
    @ApiOperation(value = "Get geo by ID")
    @ResponseBody
    public GeoInfo getCityById(@PathVariable("geoID") Integer id) {
        return this.cityService.getGeoById(id);
    }


    @RequestMapping(value = "/geo", method = RequestMethod.GET)
    @ApiOperation(value = "Get all geos")
    @ResponseBody
    public List<GeoInfo> getAllCity(){
        return this.cityService.getAllGeo();
    }

    @RequestMapping(value = "/geo", method = RequestMethod.POST)
    @ApiOperation(value = "Add a geoInfo")
    @ResponseBody
    public void addCity(@RequestBody GeoInfo geoInfo){
        this.cityService.addGeo(geoInfo);
    }

    @RequestMapping(value = "/geo/{geoID}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a city by ID")
    @ResponseBody
    public void deleteCityById(@RequestBody Integer id) {
        this.cityService.deleteGeo(id);
    }
}
