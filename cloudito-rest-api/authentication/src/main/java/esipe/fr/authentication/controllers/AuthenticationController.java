package esipe.fr.authentication.controllers;

import esipe.fr.model.AuthStatus;
import esipe.fr.authentication.exceptions.AuthenticationException;
import esipe.fr.authentication.services.AuthenticationService;
import esipe.fr.model.Credentials;
import esipe.fr.model.Customer;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.UUID;

@RestController
@Api(tags = "Authentication")
public class AuthenticationController {
    private Logger logger = LogManager.getLogger("AuthenticationController");

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/authentGoogle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "GET A RANDOM KEY", nickname = "GET KEY", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - String ", response = String.class) })
    @ResponseBody
    public ResponseEntity<String> authentGoogle()  {
        String response = authenticationService.getRandomSecretKey();
        return ResponseEntity
                .ok()
                .body(response);
    }

    @RequestMapping(value = "/authentGoogle/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "GET CODE OF KEY", nickname = "GET CODE", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - String ", response = String.class) })
    @ResponseBody
    public ResponseEntity<String> authentGoogleKey(@ApiParam(value = "Key",required = true) @PathVariable("key") String id)  {
        String response = authenticationService.getTOTPCode(id);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @RequestMapping(value = "/authenticate/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "LOG IN", nickname = "LOG IN", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - AuthStatus ", response = AuthStatus.class),
            @ApiResponse(code = 503, message = "Forbidden Access", response = String.class) })
    @ResponseBody
    public ResponseEntity<Object> firstAuthent(@RequestBody Credentials credential)  {
        try {
            Customer customer = authenticationService.getCustomer(credential.getLogin());
            if (authenticationService.verifyLoginPassword(credential.getLogin(), credential.getPwd(),customer.getId())){
                UUID uuid = UUID.randomUUID();
                customer.setUuid(uuid);
                customer.setTime(Calendar.getInstance().getTime());
                authenticationService.save(customer);
                return ResponseEntity
                        .ok()
                        .body(new AuthStatus(1, customer.getId(), uuid));
            }else{
                return ResponseEntity
                        .status(503)
                        .body("Forbidden Access");
            }
        }catch(AuthenticationException e){
            e.printStackTrace();
            return ResponseEntity
                    .status(503)
                    .body("Forbidden Access");
        }
    }

    @RequestMapping(value = "/authenticate/code", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "LOG IN WITH CODE", nickname = "LOG IN WITH CODE", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - AuthStatus ", response = AuthStatus.class),
            @ApiResponse(code = 503, message = "Forbidden Access", response = String.class) })
    @ResponseBody
    public ResponseEntity<Object> secondAuthent(@RequestBody Credentials credential)  {
        boolean authCorrect = false;
        try {
            authCorrect = (authenticationService.verifyCode(credential.getCode(),credential.getUsrId()) &&
                    authenticationService.verifyUUID(credential.getUuid(),credential.getUsrId()) &&
                    authenticationService.verifyTime(Calendar.getInstance().getTime(),credential.getUsrId()));

            if(authCorrect){
                Customer customer = authenticationService.getCustomer(credential.getUsrId());
                UUID uuid = UUID.randomUUID();
                customer.setUuid(uuid);
                return ResponseEntity
                        .ok()
                        .body(new AuthStatus(2,customer.getId(),uuid));
            }else{
                return ResponseEntity
                        .status(503)
                        .body("Forbidden Access");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(503)
                    .body("Forbidden Access");
        }

    }

    @RequestMapping(value = "/authenticate/inscription", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "REGISTER", nickname = "REGISTER", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - String ", response = String.class),
            @ApiResponse(code = 204, message = "User Mail already used", response = String.class) })
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody Credentials credential)  {
        String response = "OK";
        return ResponseEntity
                .ok()
                .body(response);
    }
}
