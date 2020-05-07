package esipe.fr.cloudito_recognition.controllers;


import esipe.fr.cloudito_model.CustomerDetection;
import esipe.fr.cloudito_model.DetectionList;
import esipe.fr.cloudito_recognition.exceptions.ApiException;
import esipe.fr.cloudito_recognition.services.RecognitionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Recognition")
public class RecognitionController {
    @Autowired
    RecognitionService recognitionService;

    @RequestMapping(value = "/recognitions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a customer face recognition", nickname = "addRecognition", response = CustomerDetection.class, tags={"Recognition"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Recognition", response = CustomerDetection.class),
            @ApiResponse(code = 404, message = "ERROR - No customer found"),
            @ApiResponse(code = 400, message = "ERROR - Content error")})
    @ResponseBody
    public ResponseEntity<CustomerDetection> addRecognition(@ApiParam(value = "Customer Recognition",required=true) @RequestBody CustomerDetection recognition) throws ApiException {
        CustomerDetection cd = this.recognitionService.addRecognition(recognition);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cd);
    }


    @ApiOperation(value = "get all face detections", nickname = "getDetections", notes = "", response = DetectionList.class, tags={"Recognition"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer detection list", response = DetectionList.class) })
    @GetMapping(value = "/recognitions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetectionList> getDetections(@ApiParam(value = "page number") @Valid @RequestParam(value = "page", required = false) Integer page, @ApiParam(value = "results per page") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new ResponseEntity<DetectionList>(this.recognitionService.findAll(page,pageSize), HttpStatus.OK);
    }
}
