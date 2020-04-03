import esipe.fr.authentication.services.AuthenticationService;
import esipe.fr.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthenticationServiceTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public AuthenticationService geolocationService() {
            return new AuthenticationService();
        }
    }

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


}
