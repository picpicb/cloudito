package esipe.fr.customer.controllers;


import esipe.fr.customer.entities.Workflow;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController {

    private static final String template = "Hello, %s!";

    @RequestMapping(value = "workflow/", method = RequestMethod.GET)
    public Workflow workflow(@RequestParam(value="name", defaultValue="World") String name) {
        return new Workflow(1,name,1);
    }
    @RequestMapping(value = "workflow/text/", method = RequestMethod.GET)
    public String workflows(){
        return "tewt workflow";
    }
}
