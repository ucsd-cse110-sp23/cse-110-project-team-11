/* 
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    
    private final String smtpHost;
    private final String smtpPort;
    private String username;
    private String password;

    public EmailService(String smtpHost, String smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }
    
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
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
*/

public interface EmailService {
    //void setUsernameAndPassword(String username, String password);
    //void setApiKey(String apiKey);
    boolean sendEmail(String to, String subject, String text);
}

