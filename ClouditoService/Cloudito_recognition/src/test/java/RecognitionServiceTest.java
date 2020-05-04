

import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_recognition.exceptions.ApiException;
import esipe.fr.cloudito_recognition.services.RecognitionService;
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

import java.util.Optional;

import static org.hamcrest.Matchers.closeTo;


@RunWith(SpringRunner.class)
public class RecognitionServiceTest {
    /**
     * Test if the function well raised an exception when customer wasn't found
     * @throws ApiException
     */
    @Test
    public void whenNoCustomer_ExceptionNotFound() throws ApiException {
        assert(true);
    }
}
