

import esipe.fr.recognition.exceptions.ApiException;
import esipe.fr.recognition.services.RecognitionService;
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

import java.util.Date;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;


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
    public void setUp() {
        


    }

    @Test
    public void whenIsNotAJSONarray_recognitionShloudNotWork() throws ApiException {

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


}
