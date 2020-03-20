package esipe.fr.authentication.controllers;

import esipe.fr.authentication.services.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
}
