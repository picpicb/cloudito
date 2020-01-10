package java;

import esipe.fr.geolocation.controllers.GeolocationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
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
