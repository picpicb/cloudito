import esipe.fr.notification.controllers.NotificationController;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.servlet.server.Session;
import java.io.IOException;
import static org.junit.Assert.*;

@DataJpaTest
public class NotificationServiceTest {

    @Mock
    private Session session;

    @Test
    public void onSendSpecificTarget() {
        //Check if the right notification is sent to the right target
    }

    @Test
    public void onSendAll() {
        //Check if sending is completed with no error
    }

    //Check if user is disconnected
    @Test
    public void onClose() throws IOException {
        NotificationController nc = new NotificationController();
        nc.onOpen(session);
        nc.onClose(session);
        assertTrue(nc.clients.isEmpty());
    }

    @Test
    public void onCreate(){
        //Check the result of creation of single notification
    }

    //Check if user is registered
    @Test
    public void onOpen() throws IOException {
        NotificationController nc = new NotificationController();
        nc.onOpen(session);
        assertFalse(nc.clients.isEmpty());
    }
}
