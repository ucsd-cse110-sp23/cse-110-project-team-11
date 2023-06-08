/*import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {

    private final String smtpHost;
    private final String smtpPort;
    private String username;
    private String password;

    /* 
    public EmailServiceImpl(String smtpHost, String smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }
    */
    /* 
    private final EmailErrorHandlingUI errorUI;

    public EmailServiceImpl(String smtpHost, String smtpPort, EmailErrorHandlingUI errorUI) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.errorUI = errorUI;
    }
    @Override
    public void setUsernameAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected Session createSession(Properties prop) {
        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    protected Message createMessage(Session session) throws MessagingException {
        return new MimeMessage(session);
    }

    @Override
    public boolean sendEmail(String to, String subject, String text) {
        if (username == null || password == null) {
            System.err.println("SMTP host not configured. Please setup the mail first.");
            return false;
        }

        if (to == null || subject == null || text == null) {
            System.err.println("To address, subject or text missing. Please provide valid email data.");
            return false;
        }

        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", smtpPort);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = createSession(prop);

        try {
            Message message = createMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Email successfully sent.");

            return true;
        } catch (AddressException e) {
            e.printStackTrace();
            errorUI.displayError(e);
            return false;
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            errorUI.displayError(e);
            return false;
        } catch (SendFailedException e) {
            e.printStackTrace();
            errorUI.displayError(e);
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            errorUI.displayError(e);
            return false;
        }
    }
}
*/

//Factory Pattern
import java.io.IOException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class EmailServiceImpl implements EmailService {

    private String apiKey;

    // Constructor to initialize the apiKey
    public EmailServiceImpl(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public boolean sendEmail(String to, String subject, String text) {
        Email from = new Email("pk2758580838@gmail.com");
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", text);
        Mail mail = new Mail(from, subject, toEmail, content);

        // Instead of creating SendGrid instance directly, 
        // we use the factory method createSendGrid().
        SendGrid sg = createSendGrid();
        
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return response.getStatusCode() == 202;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Factory Method that creates a SendGrid instance.
    // This method can be overridden by subclasses to change how SendGrid is created,
    // which is useful for testing or if we want to use a different implementation in the future.
    protected SendGrid createSendGrid() {
        return new SendGrid(apiKey);
    }
}


/*
 * powered by sendgrid and ChatGPT May 24th version
 */