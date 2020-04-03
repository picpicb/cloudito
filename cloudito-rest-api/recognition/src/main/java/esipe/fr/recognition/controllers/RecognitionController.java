package esipe.fr.recognition.controllers;

import esipe.fr.model.CustomerLocation;
import esipe.fr.recognition.services.RecognitionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Reco")
public class RecognitionController {
    @Autowired
    RecognitionService recognitionService;

    @ApiOperation(value = "Start Recognition", nickname = "recognize", tags={ "Reco"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Face received by Recognition service !") })
    @RequestMapping(value = "/recognize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> startRecognition(@ApiParam(value = "Binary data of the face",required=true) @RequestBody byte[] face) {
        recognitionService.startRecognition(face);
        return new ResponseEntity<>("Face received by Recognition service !", HttpStatus.OK);
    }

}
