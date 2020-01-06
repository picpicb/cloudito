package esipe.fr.scope3.controllers;


import esipe.fr.scope3.entities.Parcours;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "Parcours")
public class ParcoursController {


    @RequestMapping(value = "/parcours", method = RequestMethod.GET)
    @ApiOperation(value=" renvois le status du service")
    @ResponseBody
    public String chkNotifService() { return "Bonjour Parcours Service is ON.";}

    @RequestMapping(value = "/parcours/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "renvois le parcours ayant pour identifiant ID")
    @ResponseBody
    public ResponseEntity<Parcours> getParcours(@PathVariable String id) {
        try{
            //UUID uuid = UUID.fromString(id);
            Parcours p = new Parcours();
            return new ResponseEntity<Parcours>(p,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }


}
