package esipe.fr.recognition.controllers;


import esipe.fr.recognition.services.RecognitionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<String> startRecognition(@ApiParam(value = "JSON array of openCV face",required=true, example = "{array: [[152, 152 ...]]}") @RequestBody byte[] face) {
        recognitionService.startRecognition(face);
        return new ResponseEntity<>("Face received by Recognition service !", HttpStatus.OK);
    }


    @ApiOperation(value = "Get last detections of the customer", nickname = "customerRecognition", tags={ "Reco"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recognition list") })
    @GetMapping(value = "/customers/{customerID}/recognitions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> customerRecognition(@ApiParam(value = "Customer id",required = true, example = "123") @PathVariable("customerID") Long id) {
        return new ResponseEntity<>("History of the customer recognitions", HttpStatus.OK);
    }

}
