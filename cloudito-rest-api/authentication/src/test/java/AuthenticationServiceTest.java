import esipe.fr.authentication.exceptions.AuthenticationException;
import esipe.fr.authentication.services.AuthenticationService;
import esipe.fr.model.Customer;
import esipe.fr.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;
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
        //whenExistingCustomer_KeyMustBeFound
        Customer jm = new Customer();
        jm.setId(1L);
        jm.setName("jm");
        jm.setKey("ujsqzpvwwy4x4d76l2aec5cdf6edt5ww");
        Mockito.when(customerRepository.findById(jm.getId())).thenReturn(java.util.Optional.of(jm));

        Customer customerWithoutKey = new Customer();
        customerWithoutKey.setId(3L);
        customerWithoutKey.setName("NoKey");
        Mockito.when(customerRepository.findById(customerWithoutKey.getId())).thenReturn(java.util.Optional.of(customerWithoutKey));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void whenExistingCustomer_KeyMustBeFound() {
        Long idCustomer = 1L;
        Optional<Customer> jm = customerRepository.findById(idCustomer);
        assertTrue(jm.isPresent());
        assertNotNull(jm.get().getKey());
        assertEquals(jm.get().getKey(),"ujsqzpvwwy4x4d76l2aec5cdf6edt5ww");
    }

    @Test
    public void whenSameKeys_SameCodeShouldBeGenerated() {
        Long idCustomer = 1L;
        Optional<Customer> jm = customerRepository.findById(idCustomer);
        assertEquals(jm.get().getKey(),"ujsqzpvwwy4x4d76l2aec5cdf6edt5ww");
        assertEquals(authenticationService.getTOTPCode(jm.get().getKey()),authenticationService.getTOTPCode("ujsqzpvwwy4x4d76l2aec5cdf6edt5ww"));
    }

    @Test
    public void whenCustomerNotExist_ThrowException()  throws AuthenticationException{
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("No Customer found");
        Long idCustomer = 2L;
        Customer loc = authenticationService.getCustomer(idCustomer);
    }

    @Test
    public void whenCustomerExistAndKeyDont_ThrowException()  throws AuthenticationException{
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("Error, please regenerate a key");
        Long idCustomer = 3L;
        boolean auth  = authenticationService.verifyCode("352685",idCustomer);
    }

    @Test
    public void whenSameCodeThanCodeCalculatedByKey_ReturnBooleanTrue()  throws AuthenticationException{
        Long idCustomer = 1L;
        assertTrue(authenticationService.verifyCode(authenticationService.getTOTPCode("ujsqzpvwwy4x4d76l2aec5cdf6edt5ww"),idCustomer));
    }

    @Test
    public void whenDifferentCodeThanCodeCalculatedByKey_ReturnBooleanFalse()  throws AuthenticationException{
        Long idCustomer = 1L;
        assertTrue(!authenticationService.verifyCode(authenticationService.getTOTPCode("ajsqzpvwwy4x4d76l2aec5cdf6edt5ww"),idCustomer));
    }

}
