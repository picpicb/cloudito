package esipe.fr.Cloudito_geolocation.controllers;


import esipe.fr.Cloudito_geolocation.exceptions.ApiException;
import esipe.fr.Cloudito_geolocation.services.AccessPointService;
import esipe.fr.Cloudito_model.AccessPoint;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "AccessPoint")
public class AccessPointController {
    @Autowired
    AccessPointService accessPointService;

    @ApiOperation(value = "Get all access points", nickname = "accessPointGet", response = AccessPoint.class, tags={ "AccessPoint"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Access Point list", response = AccessPoint.class,responseContainer = "List") })
    @GetMapping(value = "/accesspoints",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccessPoint> accessPointGet() {
        return accessPointService.getAllAccessPoints();
    }

    @RequestMapping(value = "/accesspoints", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a access points", nickname = "addAccessPoint", response = AccessPoint.class, tags={"AccessPoint"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Access Point", response = AccessPoint.class)})
    @ResponseBody
    public ResponseEntity<AccessPoint> addAccessPoint(@ApiParam(value = "Access Point",required=true) @RequestBody AccessPoint accessPoint) throws ApiException {
        AccessPoint ap = this.accessPointService.addAccessPoints(accessPoint);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ap);
    }


}
