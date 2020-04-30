

import esipe.fr.Cloudito_model.Customer;
import esipe.fr.Cloudito_recognition.exceptions.ApiException;
import esipe.fr.Cloudito_recognition.services.RecognitionService;
import esipe.fr.Cloudito_repositories.CustomerRepository;
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

import java.util.Optional;

import static org.hamcrest.Matchers.closeTo;


@RunWith(SpringRunner.class)
public class RecognitionServiceTest {

    @TestConfiguration
    static class RecognitionServiceImplTestContextConfiguration {

        @Bean
        public RecognitionService recognitionService() {
            return new RecognitionService();
        }
    }

    @Autowired
    private RecognitionService recognitionService;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() { }


    @Test
    public void whenConnectionIsUp() {
        //Will be activated when the service will be coded
       // assertEquals("Socket must be opened",new Socket(),recognitionService.openConnection());
    }

    /**
     * Test if the function well raised an exception when the input is incorrect
     * @throws ApiException
     */
    @Test
    public void whenIsNotAJSONarray_recognitionShloudNotWork() throws ApiException {
        //exceptionRule.expect(ApiException.class);
        //exceptionRule.expectMessage("content error");
        byte[] input = {1,2,3};
        //Will be activated when the service will be coded
        //recognitionService.startRecognition(input);
        Optional<Customer> customer = customerRepository.findById(Long.parseLong("1"));
    }

    /**
     * Test if the function well raised an exception when customer wasn't found
     * @throws ApiException
     */
    @Test
    public void whenNoCustomer_ExceptionNotFound() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("No Customer found");
        Long idCustomer = 3L;
        recognitionService.getLastCustomerDetection(idCustomer);
    }

    /**
     * Test if the function well raised an exception when no detection was found
     * @throws ApiException
     */
    @Test
    public void whenNoDetection_ExceptionNotFound() throws ApiException {
        exceptionRule.expect(ApiException.class);
        exceptionRule.expectMessage("Any detection found");
        Long idCustomer = 0L;
        recognitionService.getLastCustomerDetection(idCustomer);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

}
