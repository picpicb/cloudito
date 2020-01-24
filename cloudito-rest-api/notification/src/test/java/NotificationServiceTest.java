import esipe.fr.notification.ClouditoNotificationService;
import esipe.fr.notification.controllers.NotificationController;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.servlet.server.Session;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
public class NotificationServiceTest {

    @Mock
    //private Session session;
    private Session session = Mockito.mock(Session.class);
    @Mock
 //   private NotificationController nc;
    private NotificationController nc = Mockito.mock(NotificationController.class);

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
        //NotificationController nc = new NotificationController();
        nc.onOpen(session);
        nc.onClose(session);
        assertTrue(nc.getClients().isEmpty());
    }

    @Test
    public void onCreate(){
        //Check the result of creation of single notification
    }

    //Check if user is registered
    @Test
    public void onOpen() throws IOException {
        nc.onOpen(session);
        //assertFalse(nc.getClients().isEmpty());
    }
}
