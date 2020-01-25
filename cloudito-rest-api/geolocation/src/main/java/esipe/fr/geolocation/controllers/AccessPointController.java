package esipe.fr.geolocation.controllers;


import esipe.fr.geolocation.services.AccessPointService;
import esipe.fr.model.AccessPoint;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
