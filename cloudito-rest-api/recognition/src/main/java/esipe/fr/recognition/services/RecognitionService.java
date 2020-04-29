package esipe.fr.recognition.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class RecognitionService {

    private Logger logger = LogManager.getLogger("RecognitionService");

    public void startRecognition(byte[] face){
        logger.info("New face detected");
        logger.info("Start recognition");
        String personName = "";
        Socket soc = openConnection();
        try{
            //open streams
           // Socket soc = new Socket("localhost",2021);
            OutputStream out = soc.getOutputStream();
            InputStream in = soc.getInputStream();

            //building writer and reader
            PrintWriter writer = new PrintWriter(out, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            writer.println("HELLO");

            String line;
            while ((line = reader.readLine()) != null) {
                logger.info("MSG : "+line);
                switch (line){
                    case "HELLOACK" :
                        out.write(face);
                        break;
                    case "FACEACK" :
                        personName = reader.readLine();
                        logger.info("Personne recognized : "+personName);
                        writer.println("BYE");
                        break;
                    default :
                        break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Open a new connection
     * @return socket created
     */
    public Socket openConnection(){
        Socket soc = new Socket();
        try{
            soc = new Socket("localhost",2021);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soc;
    }


}
