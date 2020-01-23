package esipe.fr.geolocation.controllers;



import esipe.fr.geolocation.entities.AccessPoint;
import esipe.fr.geolocation.entities.UserLocation;
import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.geolocation.services.GeolocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Geo")
public class GeolocationController {
    @Autowired
    private GeolocationService geolocationServiceService;



    @RequestMapping(value = "/users/{userID}/location", method = RequestMethod.GET)
    @ApiOperation(value = "Get location by userID", nickname = "getUserLocation", notes = "", response = UserLocation.class, tags={ "Geo", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - User Location", response = UserLocation.class) })
    @ResponseBody
    public ResponseEntity<UserLocation> getCityById(@PathVariable("userID") Long id) throws ApiException {
        UserLocation userLocation = this.geolocationServiceService.getUserLocation(id);
        return ResponseEntity
                .ok()
                .body(userLocation);
    }


    @RequestMapping(value = "/users/{userID}/location", method = RequestMethod.POST)
    @ApiOperation(value = "Add a user location", nickname = "addUserLocation", notes = "", response = UserLocation.class, tags={ "Geo", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "user location created", response = UserLocation.class)})
    @ResponseBody
    public ResponseEntity<UserLocation> addCity(@RequestBody UserLocation userLocation) throws ApiException {
        UserLocation ul = this.geolocationServiceService.addUserLocation(userLocation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ul);
    }


}
