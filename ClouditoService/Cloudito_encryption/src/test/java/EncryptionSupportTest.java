import esipe.fr.cloudito_encryption.ClouditoEncryptionService;
import esipe.fr.cloudito_encryption.EncryptionSupport;

import static org.junit.Assert.*;

import esipe.fr.cloudito_encryption.model.AttributeEncryptor;
import esipe.fr.cloudito_encryption.model.Coordinate;
import esipe.fr.cloudito_encryption.persistence.CoordinateRepository;
import esipe.fr.cloudito_encryption.persistence.CoordinateRowMapper;
import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_model.Location;
import esipe.fr.cloudito_repositories.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClouditoEncryptionService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EncryptionSupportTest {


    @Autowired
    private AttributeEncryptor attributeEncryptor;
    private static String stringTest = "Bonjour";

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    CoordinateRepository coordinateRepository;

    @Test
    public final void testDatabaseEncryptionIntegration(){
        int x = 1;
        int y = 2;
        String name = "facticeName";
        long id = 1l;
        Coordinate newCoordinate = new Coordinate(id,x,y,name);
        coordinateRepository.deleteAll();
        coordinateRepository.save(newCoordinate);
        System.out.println("Coordinate Saved");


        Optional<Coordinate> coordinateFound = coordinateRepository.findById(id);
        System.out.println("Coordinate Found with JPA");

        //tester comme quoi  x chiffré et y en clair avec var x et var y

        String sql = "SELECT * FROM coordinate WHERE id = ?";
        Coordinate foundCoordinate = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CoordinateRowMapper());
        System.out.println("Coordinate Found with JBDC");

        System.out.println(foundCoordinate);
    }


    @Test
    public final void testVaultIntegration(){
        if(attributeEncryptor == null){
            assert (false);
        }else {
           String converted = attributeEncryptor.convertToDatabaseColumn(stringTest);
           assertNotEquals(stringTest,converted);
           String clearString = attributeEncryptor.convertToEntityAttribute(converted);
           assertEquals(stringTest,clearString);
        }
    }


    @Test
    public final void testEncryption() {
        EncryptionSupport AES = new EncryptionSupport();

        String secretKey = "test";
        String clearString = "stringenclair";
        String expectedEncryptedString = "5GKqlX/fUrOjE6TGxIJn/Q==";

        String encryptedString = AES.encrypt(clearString, secretKey) ;

        assertEquals(expectedEncryptedString,encryptedString);
    }

    @Test
    public final void testDecryption() {
        EncryptionSupport AES = new EncryptionSupport();

        String secretKey = "test";
        String encryptedString = "5GKqlX/fUrOjE6TGxIJn/Q==";
        String expectedClearString = "stringenclair";

        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        assertEquals(decryptedString,expectedClearString);
    }

}
