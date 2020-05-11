

import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_model.CustomerDetection;
import esipe.fr.cloudito_recognition.exceptions.ApiException;
import esipe.fr.cloudito_recognition.services.RecognitionService;
import esipe.fr.cloudito_repositories.CustomerDetectionRepository;
import esipe.fr.cloudito_repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.closeTo;


@RunWith(SpringRunner.class)
public class RecognitionServiceTest {


    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public RecognitionService recognitionService() {
            return new RecognitionService();
        }
    }

    @Autowired
    private RecognitionService recognitionService;

    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private CustomerDetectionRepository customerDetectionRepository;


    @Before
    public void setUp() {

        // whenNoCustomer_ExceptionNotFound setup
        Customer jm = new Customer();
        jm.setId(1L);
        jm.setName("jm");
        Mockito.when(customerRepository.findById(jm.getId())).thenReturn(java.util.Optional.of(jm));

    }



    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Test if the function well raised an exception when customer wasn't found
     * @throws ApiException
     */
    @Test
    public void whenNoCustomer_ExceptionNotFound() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("No Customer found");
        Long idCustomer = 3L;
        CustomerDetection cd = new CustomerDetection();
        cd.setCustomerId(idCustomer);
        cd.setRecognitionDate(new Date());
        recognitionService.addRecognition(cd);
    }

    /**
     * Test if the function well raised an exception when try to save an empty customer Dectection (NO DATE)
     * @throws ApiException
     */
    @Test
    public void whenEmptyCustomerDectection_ExceptionContentError1() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("content error");
        Long idCustomer = 1L;
        CustomerDetection cd = new CustomerDetection();
        cd.setCustomerId(idCustomer);
        recognitionService.addRecognition(cd);
    }

    /**
     * Test if the function well raised an exception when try to save an empty customer Dectection (NO CUSTOMER)
     * @throws ApiException
     */
    @Test
    public void whenEmptyCustomerDectection_ExceptionContentError2() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("content error");
        CustomerDetection cd = new CustomerDetection();
        cd.setRecognitionDate(new Date());
        recognitionService.addRecognition(cd);
    }
}
