import esipe.fr.cloudito_authentication.exceptions.AuthenticationException;
import esipe.fr.cloudito_authentication.services.AuthenticationService;
import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_repositories.CustomerRepository;
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
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

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

    private UUID uuid = UUID.randomUUID();

    @Before
    public void setUp() {
        //whenExistingCustomer_KeyMustBeFound
        Customer jm = new Customer();
        jm.setId(1L);
        jm.setName("jm");
        jm.setsKey("ujsqzpvwwy4x4d76l2aec5cdf6edt5ww");
        jm.setTime(Calendar.getInstance().getTime());
        jm.setUuid(this.uuid);
        jm.setLogin("jm@gmail.com");
        jm.setPwd("motDePasseDeTest");
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
        assertNotNull(jm.get().getsKey());
        assertEquals(jm.get().getsKey(),"ujsqzpvwwy4x4d76l2aec5cdf6edt5ww");
    }

    @Test
    public void whenSameKeys_SameCodeShouldBeGenerated() {
        Long idCustomer = 1L;
        Optional<Customer> jm = customerRepository.findById(idCustomer);
        assertEquals(jm.get().getsKey(),"ujsqzpvwwy4x4d76l2aec5cdf6edt5ww");
        assertEquals(authenticationService.getTOTPCode(jm.get().getsKey()),authenticationService.getTOTPCode("ujsqzpvwwy4x4d76l2aec5cdf6edt5ww"));
    }

    @Test
    public void whenCustomerNotExist_ThrowException()  throws AuthenticationException{
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("No Customer found");
        Long idCustomer = 2L;
        Customer loc = authenticationService.getCustomer(idCustomer);
    }

    @Test
    public void whenCustomerLoginAndPasswordNotExist_ThrowException()  throws AuthenticationException{
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("No Customer found");
        Long idCustomer = 2L;
        Customer loc = authenticationService.getCustomer("login");
    }

    @Test
    public void whenLoginAndPasswordAreCorrect_ReturnTrue() throws AuthenticationException {
        Long idCustomer = 1L;
        assertTrue(authenticationService.verifyLoginPassword("jm@gmail.com","motDePasseDeTest",idCustomer));
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

    @Test
    public void whenTokenIsFalse_ReturnFalse() throws AuthenticationException {
        Long idCustomer = 1L;
        assertTrue(!authenticationService.verifyUUID(UUID.randomUUID(),idCustomer));
    }

    @Test
    public void whenSecondAuthentIsToLate_ReturnFalse() throws AuthenticationException {
        Long idCustomer = 1L;
        long time = Calendar.getInstance().getTime().getTime()+350000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        assertTrue(!authenticationService.verifyTime(calendar.getTime(),idCustomer));
    }

    @Test
    public void whenTokenIsNull_ThrowException()throws AuthenticationException{
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("Error, WRONG TOKEN");
        Long idCustomer = 3L;
        boolean auth  = authenticationService.verifyUUID(UUID.randomUUID(),idCustomer);
    }

    @Test
    public void whenDateIsNull_ThrowException()throws AuthenticationException{
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("Error, WRONG DATE");
        Long idCustomer = 3L;
        boolean auth  = authenticationService.verifyTime(Calendar.getInstance().getTime(),idCustomer);
    }

    @Test
    public void whenTokenIsCorrect_ReturnTrue() throws AuthenticationException {
        Long idCustomer = 1L;
        assertTrue(authenticationService.verifyUUID(uuid,idCustomer));
    }

    @Test
    public void whenDateIsCorrect_ReturnTrue() throws AuthenticationException {
        Long idCustomer = 1L;
        long time = Calendar.getInstance().getTime().getTime()+3000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        assertTrue(authenticationService.verifyTime(calendar.getTime(),idCustomer));
    }
}
