package esipe.fr.course.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    public ArrayList<ArrayList> liste;
    public String debug;
    public Map(ArrayList<ArrayList> liste) {
        this.liste = liste;
        this.debug = "";
    }

    public ArrayList<ArrayList> getListe() {
        return liste;
    }

    public void setListe(ArrayList<ArrayList> liste) {
        this.liste = liste;
    }
    public Map build(){
        ArrayList<ArrayList> liste = new ArrayList<>();
        Map map = new Map(liste);
        try{

            final Logger log = LoggerFactory.getLogger(this.getClass());
            File file = ResourceUtils.getFile("classpath:map.json");
            JsonNode node = new ObjectMapper().readTree(file);
            ArrayNode polygons = (ArrayNode) node.path("pois").path("polygons");
            for(JsonNode j : polygons){
                //recuperation des polygons

                JsonNode points = j.path("points");
                String[] s = points.asText().split(",");
                ArrayList<Coordinate> poly = new ArrayList<>();
                for(String s2 : s){
                    String p1 = s2.split(" ")[0];
                    String p2 = s2.split(" ")[1];
                    poly.add(new Coordinate(Double.parseDouble(p1),Double.parseDouble(p2)));
                }
                liste.add(poly);
            }
            log.debug("test\n");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        //lecture du fichier

        return map;
    }
}
