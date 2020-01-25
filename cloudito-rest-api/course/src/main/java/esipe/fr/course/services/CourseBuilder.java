package esipe.fr.course.services;

import com.google.gson.reflect.TypeToken;
import esipe.fr.course.entities.Course;
import esipe.fr.course.entities.Store;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;

public class CourseBuilder {
    public static Course generateCourseForCustomer(UUID id){
        Course c = new Course();
        //recuperation de la liste des magasins
        //utilisationd'une autre api
        String uri = "http://172.31.254.54:3081/stores";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return c;
    }
}
