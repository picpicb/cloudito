package esipe.fr.authentication.controllers;

import esipe.fr.authentication.entities.TOTP;
import esipe.fr.authentication.services.AuthenticationService;
import io.swagger.annotations.*;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
@Api(tags = "Authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/authentGoogle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "launch second Auth", nickname = "authentGoogle", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - String ", response = String.class) })
    @ResponseBody
    public ResponseEntity<String> authentGoogle()  {
        String response = getRandomSecretKey();
        return ResponseEntity
                .ok()
                .body(response);
    }

    @RequestMapping(value = "/authentGoogle/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "launch second Auth", nickname = "authentGoogle", response =String.class, tags={"Auth"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - String ", response = String.class) })
    @ResponseBody
    public ResponseEntity<String> authentGoogleKey(@ApiParam(value = "Key",required = true) @PathVariable("key") String id)  {
        String response = getTOTPCode(id);
        return ResponseEntity
                .ok()
                .body(response);
    }

    private String getRandomSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        String secretKey = base32.encodeToString(bytes);
        // make the secret key more human-readable by lower-casing and
        // inserting spaces between each group of 4 characters
        return secretKey.toLowerCase().replaceAll("(.{4})(?=.{4})", "$1 ");
    }

    public String getTOTPCode(String secretKey) {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (System.currentTimeMillis() / 1000) / 30;
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, "6");
    }
}
