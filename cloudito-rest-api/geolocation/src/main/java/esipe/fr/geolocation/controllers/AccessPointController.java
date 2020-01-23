package esipe.fr.geolocation.controllers;

import esipe.fr.geolocation.entities.AccessPoint;
import esipe.fr.geolocation.services.AccessPointService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccessPointController {
    @Autowired
    AccessPointService accessPointService;

    @ApiOperation(value = "retourne tous les AP", nickname = "accessPointGet", notes = "", response = AccessPoint.class, tags={ "AccessPoint", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Access Point list", response = AccessPoint.class) })
    @GetMapping(value = "/accesspoints",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccessPoint> accessPointGet() {
        return accessPointService.getAllAccessPoints();
    }
}
