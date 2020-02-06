import esipe.fr.geolocation.ClouditoGeolocationService;
import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.geolocation.services.GeolocationService;
import esipe.fr.model.Customer;
import esipe.fr.model.CustomerLocation;
import esipe.fr.model.Location;
import esipe.fr.repositories.CustomerLocationRepository;
import esipe.fr.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class GeolocationServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public GeolocationService geolocationService() {
            return new GeolocationService();
        }
    }

    @Autowired
    private GeolocationService geolocationService;

    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private CustomerLocationRepository customerLocationRepository;


    @Before
    public void setUp() {
        Customer jm = new Customer();
        jm.setId(1L);
        jm.setName("jm");
        Location loc = new Location();
        loc.setFloor(0);
        loc.setX(22.2);
        loc.setY(33.3);

        CustomerLocation custoLoc = new CustomerLocation();
        custoLoc.setCustomer(jm);
        custoLoc.setId(1L);
        custoLoc.setLastUpdate(new Date());
        custoLoc.setLocation(loc);

        Mockito.when(customerRepository.findById(jm.getId())).thenReturn(java.util.Optional.of(jm));

        Mockito.when(customerLocationRepository.findFirstByCustomerIdOrderByLastUpdateDesc(jm.getId()))
                .thenReturn(custoLoc);
    }

    @Test
    public void whenExistingUser_locationShouldBeFound() throws ApiException {
        Long idCustomer = 1L;
        CustomerLocation loc = geolocationService.getCustomerLocation(idCustomer);
        assertThat(loc.getLocation().getX(),closeTo(22.2, 0.0));
        assertThat(loc.getLocation().getY(),closeTo(33.3, 0.0));
    }


}
