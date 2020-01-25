package esipe.fr.course.controllers;


import esipe.fr.course.entities.Course;
import esipe.fr.course.entities.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Courses")
public class CourseController {

    @Autowired
    ResourceLoader loader;

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "renvois le parcours ayant pour identifiant l'ID de l'utilisateur")
    @ResponseBody
    public ResponseEntity<Course> getCourse(@PathVariable String id) {
        try{
            //UUID uuid = UUID.fromString(id);
            Course p = Course.build();
            return new ResponseEntity<Course>(p,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/courses/{id}/terminate", method = RequestMethod.GET)
    @ApiOperation(value = "Termine le parcours d'un utilisateur")
    @ResponseBody
    public ResponseEntity<Course> terminateCourse(@PathVariable String id){
        try{
            Course p = Course.build();
            p.setStatus("terminated");
            return new ResponseEntity<Course>(p,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }




}
