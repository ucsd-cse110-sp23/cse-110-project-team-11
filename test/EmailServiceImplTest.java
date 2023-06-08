/*import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmailServiceTest {
    private EmailService emailService;
    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "587";
    private final String username = "cse110Team11@gmail.com";
    private final String password = "ucsdCSE110team11";

    @Mock
    private Session session;

    @Mock
    private MimeMessage message;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emailService = new EmailService(smtpHost, smtpPort);
        emailService.setUsernameAndPassword(username, password);
    }

    @Test
    public void testSendEmailSuccess() throws MessagingException {
        when(emailService.createSession(any(Properties.class))).thenReturn(session);
        when(emailService.createMessage(any(Session.class))).thenReturn(message);

        String to = "Hongyuanjia8@gmail.com";
        String subject = "Test";
        String text = "This is a test.";

        assertTrue(emailService.sendEmail(to, subject, text));

        verify(message).setFrom(any(Address.class));
        verify(message).setRecipients(any(Message.RecipientType.class), any(Address[].class));
        verify(message).setSubject(anyString());
        verify(message).setText(anyString());

        verify(Transport.class);
        Transport.send(message);
    }

    @Test
    public void testSendEmailFailed() throws MessagingException {
        when(emailService.createSession(any(Properties.class))).thenReturn(session);
        when(emailService.createMessage(any(Session.class))).thenThrow(new MessagingException("Failed to create message"));

        String to = "test@example.com";
        String subject = "Test";
        String text = "This is a test.";

        assertFalse(emailService.sendEmail(to, subject, text));
    }

    @Test
    public void testSMTPServerNotAvailable() throws MessagingException {
        when(emailService.createSession(any(Properties.class))).thenThrow(new MessagingException("Failed to create session"));

        String to = "test@example.com";
        String subject = "Test";
        String text = "This is a test.";

        assertFalse(emailService.sendEmail(to, subject, text));
    }
}
*/

/*Real */
/* 
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmailServiceImplTest {

    @Mock
    private EmailService emailService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendEmailSuccess() {
        String to = "test@example.com";
        String subject = "Test";
        String text = "This is a test.";

        when(emailService.sendEmail(to, subject, text)).thenReturn(true);

        assertTrue(emailService.sendEmail(to, subject, text));
    }

    @Test
    public void testSendEmailFailed() {
        String to = "test@example.com";
        String subject = "Test";
        String text = "This is a test.";

        when(emailService.sendEmail(to, subject, text)).thenReturn(false);

        assertFalse(emailService.sendEmail(to, subject, text));
    }
}
*/

/*Using Gmail to test */
/*
import com.icegreen.greenmail.junit.GreenMailRule;
import static org.junit.Assert.*;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.Rule;
import org.junit.Test;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;

public class EmailServiceImplTest {

    @Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);

    @Test
    public void testSendEmail() throws Exception {
        EmailServiceImpl emailService = new EmailServiceImpl("smtp.gmail.com", "587");
        emailService.setUsernameAndPassword("cse110Team11@gmail.com", "ucsdCSE110team11");

        String to = "Hongyuanjia8@gmail.com";
        String subject = "Test";
        String text = "This is a test.";

        assertTrue(emailService.sendEmail(to, subject, text));

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        assertEquals(subject, receivedMessages[0].getSubject());
        assertEquals(text, receivedMessages[0].getContent().toString().trim());
    }
}
*/

//Send email

/*import org.junit.Test;
import static org.junit.Assert.*;

public class EmailServiceImplTest {

    @Test
    public void testSendEmail() {
        EmailServiceImpl emailService = new EmailServiceImpl("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");
        String to = "Hongyuanjia8@gmail.com";
        String subject = "Test";
        String text = "This is a test";

        boolean sendEmailResult = emailService.sendEmail(to, subject, text);
        assertTrue(sendEmailResult);
    }
}*/
/*
 * powered by sendgrid and ChatGPT May 24th version
 */



 /* 
import org.junit.Test;
import static org.junit.Assert.*;

public class EmailServiceImplTest {

    @Test
    public void testSendEmail() {
        EmailServiceImpl emailService = new EmailServiceImpl("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");
        String to = "Hongyuanjia8@gmail.com";
        String subject = "Test";
        String text = "This is a test";

        boolean sendEmailResult = emailService.sendEmail(to, subject, text);
        assertTrue(sendEmailResult);
    }
}*/
/*
 * powered by sendgrid and ChatGPT May 24th version
 */

 import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.sendgrid.*;

public class EmailServiceImplTest {

    @Test
    public void testSendEmail() {
        String apiKey = "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";
        SendGrid mockSendGrid = mock(SendGrid.class);
        Response mockResponse = mock(Response.class);
        when(mockResponse.getStatusCode()).thenReturn(202);
        
        EmailServiceImpl emailService = new EmailServiceImpl(apiKey) {
            @Override
            protected SendGrid createSendGrid() {
                return mockSendGrid;
            }
        };

        try {
            Request mockRequest = any(Request.class);
            when(mockSendGrid.api(mockRequest)).thenReturn(mockResponse);
        } catch (IOException ex) {
            fail("Mock SendGrid threw exception");
        }
        
        assertTrue(emailService.sendEmail("hongyuanjia8@gmail.com", "Subject", "Text"));
    }
}