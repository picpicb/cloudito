import esipe.fr.geolocation.ClouditoGeolocationService;
import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.geolocation.services.GeolocationService;
import esipe.fr.model.Customer;
import esipe.fr.model.CustomerLocation;
import esipe.fr.model.Location;
import esipe.fr.repositories.CustomerLocationRepository;
import esipe.fr.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
        
        // whenExistingUser_locationShouldBeFound setup
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
        
        // whenNoLocation_ExceptionNotFound setup
        Customer jm2 = new Customer();
        jm2.setId(2L);
        jm.setName("jm2");
        CustomerLocation custoLoc2 = new CustomerLocation();
        custoLoc2.setCustomer(jm2);
        custoLoc2.setId(2L);
        custoLoc2.setLastUpdate(new Date());
        custoLoc2.setLocation(null);

        Mockito.when(customerRepository.findById(jm2.getId())).thenReturn(java.util.Optional.of(jm2));

    }

    @Test
    public void whenExistingCustomer_locationShouldBeFound() throws ApiException {
        Long idCustomer = 1L;
        CustomerLocation loc = geolocationService.getCustomerLocation(idCustomer);
        assertThat(loc.getLocation().getX(),closeTo(22.2, 0.0));
        assertThat(loc.getLocation().getY(),closeTo(33.3, 0.0));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void whenNoCustomer_ExceptionNotFound() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("No Customer found");
        Long idCustomer = 3L;
        CustomerLocation loc = geolocationService.getCustomerLocation(idCustomer);
    }


    @Test
    public void whenNoLocation_ExceptionNotFound() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("No Location found");
        Long idCustomer = 2L;
        CustomerLocation loc = geolocationService.getCustomerLocation(idCustomer);
    }

    @Test
    public void whenExistingCustomerAndLocation_locationShouldBeSaved() throws ApiException {
        Long idCustomer = 1L;
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
        CustomerLocation location = geolocationService.addCustomerLocation(idCustomer,custoLoc);
        assertThat(location.getLocation().getX(),closeTo(22.2, 0.0));
        assertThat(location.getLocation().getY(),closeTo(33.3, 0.0));
    }

    @Test
    public void whenNoLocationUpdateDate_ExceptionContentError() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("content error");
        Long idCustomer = 1L;
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
        //DATE IS NULL
        custoLoc.setLastUpdate(null);
        custoLoc.setLocation(loc);
        CustomerLocation location = geolocationService.addCustomerLocation(idCustomer,custoLoc);
    }

    @Test
    public void whenNoLocationData_ExceptionContentError() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("content error");
        Long idCustomer = 1L;
        Customer jm = new Customer();
        jm.setId(1L);
        jm.setName("jm");

        CustomerLocation custoLoc = new CustomerLocation();
        custoLoc.setCustomer(jm);
        custoLoc.setId(1L);
        custoLoc.setLastUpdate(new Date());
        //LOCATION IS NULL
        custoLoc.setLocation(null);
        CustomerLocation location = geolocationService.addCustomerLocation(idCustomer,custoLoc);
    }
}
