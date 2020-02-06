package esipe.fr.geolocation.controllers;


import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.geolocation.services.GeolocationService;
import esipe.fr.model.CustomerLocation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Geo")
public class GeolocationController {
    @Autowired
    private GeolocationService geolocationServiceService;



    @RequestMapping(value = "/customers/{customerID}/location", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get the last location of the customer", nickname = "getCustomerLocation", response = CustomerLocation.class, tags={"Geo"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Customer Location", response = CustomerLocation.class) })
    @ResponseBody
    public ResponseEntity<CustomerLocation> getCustomerLocation(@ApiParam(value = "Customer id",required = true, example = "123") @PathVariable("customerID") Long id) throws ApiException {
        CustomerLocation customerLocation = this.geolocationServiceService.getCustomerLocation(id);
        return ResponseEntity
                .ok()
                .body(customerLocation);
    }


    @RequestMapping(value = "/customers/{customerID}/location", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a customer location", nickname = "addCustomerLocation", response = CustomerLocation.class, tags={"Geo"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Customer location", response = CustomerLocation.class)})
    @ResponseBody
    public ResponseEntity<CustomerLocation> addCustomerLocation(@ApiParam(value = "Customer id",required = true, example = "123") @PathVariable("customerID") Long id, @ApiParam(value = "Customer location",required=true) @RequestBody CustomerLocation customerLocation) throws ApiException {
        CustomerLocation loc = this.geolocationServiceService.addCustomerLocation(id, customerLocation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loc);
    }


}
