package esipe.fr.course.controllers;

import esipe.fr.course.entities.Map;
import esipe.fr.course.entities.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@Api(tags = "Map")
public class MapController {
    @RequestMapping(value = "/map",method = RequestMethod.GET)
    @ApiOperation(value = "recuperation de la carte du centre")
    @ResponseBody
    public ResponseEntity<Map> getMap(){

        Map map = new Map(null);

        return new ResponseEntity<Map>(map.build(), HttpStatus.OK);

    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    @ApiOperation(value= "récuperation de la liste des magasins")
    @ResponseBody
    public ResponseEntity<ArrayList<Store>> getStores(){
        ArrayList<Store> liste = new ArrayList<>();
        return new ResponseEntity<>(liste,HttpStatus.OK);
    }
}
