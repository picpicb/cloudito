package esipe.fr.course.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import springfox.documentation.spring.web.json.Json;

import java.io.*;
import java.util.ArrayList;

public class Map {
    public ArrayList<ArrayList> liste;
    public String debug;

    private Resource ressourceMap;


    public Map(ArrayList<ArrayList> liste) {
        this.liste = liste;
        this.debug = "";
        this.ressourceMap = new ClassPathResource("map.json");
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
            File somethingFile = File.createTempFile("test", ".txt");
            InputStream inputStream = ressourceMap.getInputStream();
            try {
                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            JsonNode node = new ObjectMapper().readTree(somethingFile);
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
