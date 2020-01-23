import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
public class GeolocationServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getCorrectLocationTest() {
        // When the app give a location
        // the location is verified like the user can't be in a wall or in a hole

    }

    @Test
    public void setCorrectLocationTest() {
        // When the app give a location
        // the location is verified like the user can't be in a wall or in a hole

    }

    @Test
    public void mapIsEmptyTest() {
        // When the backend give the map
        // the map is not empty
    }


    @Test
    public void mapIsCompleteWithStoreAndAccessPoint() {
        // When the backend give the map
        // the map must be complete
    }
}
