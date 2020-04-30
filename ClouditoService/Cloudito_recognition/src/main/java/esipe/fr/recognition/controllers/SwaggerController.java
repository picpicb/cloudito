package esipe.fr.recognition.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SwaggerController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home() {
        return "redirect:/swagger-ui.html";
    }

}
