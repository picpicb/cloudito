package esipe.fr.course.controllers;


import esipe.fr.course.entities.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Courses")
public class CourceController {

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "renvois le parcours ayant pour identifiant ID")
    @ResponseBody
    public ResponseEntity<Course> getParcours(@PathVariable String id) {
        try{
            //UUID uuid = UUID.fromString(id);
            Course p = new Course();
            return new ResponseEntity<Course>(p,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

}
