package esipe.fr.recognition.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.Socket;

@Service
public class RecognitionService {

    private Logger logger = LogManager.getLogger("RecognitionService");

    public void startRecognition(String face){
        logger.info("New face detected");
        logger.info("Start recognition");
        try{
            Socket soc=new Socket("localhost",2020);
            DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
            dout.writeUTF("Hello");
            dout.flush();
            dout.close();
            soc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
