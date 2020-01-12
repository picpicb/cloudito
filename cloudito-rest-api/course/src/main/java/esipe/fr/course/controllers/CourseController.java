package esipe.fr.course.controllers;


import esipe.fr.course.entities.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Courses")
public class CourseController {

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "renvois le parcours ayant pour identifiant l'ID de l'utilisateur")
    @ResponseBody
    public ResponseEntity<Course> getCourse(@PathVariable String id) {
        try{
            //UUID uuid = UUID.fromString(id);
            Course p = new Course();
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
            Course p = new Course();
            p.setStatus("terminated");
            return new ResponseEntity<Course>(p,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }


}
