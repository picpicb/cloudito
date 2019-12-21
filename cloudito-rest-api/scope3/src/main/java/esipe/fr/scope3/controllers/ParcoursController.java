package esipe.fr.scope3.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;

@RestController
@Api(tags = "Parcours")
public class ParcoursController {


    @RequestMapping(value = "/parcours", method = RequestMethod.GET)
    @ApiOperation(value=" renvois le status du service")
    @ResponseBody
    public String chkNotifService() { return "Bonjour Parcours Service is ON.";}


}
