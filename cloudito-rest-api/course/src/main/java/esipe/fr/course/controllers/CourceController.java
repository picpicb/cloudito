package esipe.fr.course.controllers;


import esipe.fr.course.entities.Parcours;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Courses")
public class CourceController {


    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    @ApiOperation(value=" renvois le status du service")
    @ResponseBody
    public String chkNotifService() { return "Bonjour Parcours Service is ON.";}

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
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
