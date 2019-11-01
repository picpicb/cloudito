package esipe.fr.main.controllers;


import esipe.fr.main.entities.City;
import esipe.fr.main.services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "main")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(final CityService cityService){
        this.cityService = cityService;
    }

    @RequestMapping(value = "/cities/{cityId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get city by ID")
    @ResponseBody
    public City getCityById(@PathVariable("cityId") Integer id) {
        return this.cityService.getCityById(id);
    }


    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    @ApiOperation(value = "Get all cities")
    @ResponseBody
    public List<City> getAllCity(){
        return this.cityService.getAllCities();
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    @ApiOperation(value = "Add a city")
    @ResponseBody
    public void addCity(@RequestBody City city){
        this.cityService.addCity(city);
    }

    @RequestMapping(value = "/cities/{cityId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a city by ID")
    @ResponseBody
    public void deleteCityById(@RequestBody Integer id) {
        this.cityService.deleteCity(id);
    }
}
