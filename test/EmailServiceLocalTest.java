
/*
 * powered by sendgrid and ChatGPT May 24th version
 */

 import org.junit.jupiter.api.Test;

import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

public class EmailServiceLocalTest {
    @Test
    public void testSendEmail() throws IOException {
        // Create a mock SendGrid and Response objects using Mockito
        SendGrid mockSendGrid = mock(SendGrid.class);
        Response mockResponse = mock(Response.class);

        // Make the mock SendGrid return the mock Response when its api() method is called
        when(mockSendGrid.api(any())).thenReturn(mockResponse);

        // Make the mock Response return 202 when getStatusCode() is called
        when(mockResponse.getStatusCode()).thenReturn(202);

        // Create a subclass of EmailServiceImpl that returns the mock SendGrid
        EmailServiceImpl emailService = new EmailServiceImpl("fake-api-key") {
            @Override
            protected SendGrid createSendGrid() {
                return mockSendGrid;
            }
        };

        // Now we can test sendEmail without actually sending an email
        boolean result = emailService.sendEmail("Hongyuanjia8@gmail.com", "Subject", "Text");

        // Verify that sendEmail returns true, since we made the mock SendGrid return 202
        assertTrue(result);

        // Verify that SendGrid.api() was called
        verify(mockSendGrid).api(any());
    }
}
