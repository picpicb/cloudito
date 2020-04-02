package esipe.fr.recognition.controllers;

import esipe.fr.recognition.services.RecognitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Reco")
public class RecognitionController {
    @Autowired
    RecognitionService recognitionService;

    @ApiOperation(value = "Start Recognition", nickname = "recognize", tags={ "Reco"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Face retrieved") })
    @GetMapping(value = "/recognize",produces = MediaType.APPLICATION_JSON_VALUE)
    public void accessPointGet() {
        //Calling recognition deamon with no face
        //Trying socket connexion first
        String face = "Macron";
        recognitionService.startRecognition(face);
    }

}
