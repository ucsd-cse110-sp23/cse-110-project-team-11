import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private String host;
    private int port;
    private String email;
    private String to;
    private String text;

    public EmailSender(String host, String port, String email, String to, String text) {
        this.host = host;
        this.port = Integer.parseInt(port);
        this.email = email;
        this.to = to;
        this.text = text;
    }
    public void send(){

        // Set TLS properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // // Create the session with TLS enabled
        Session session = Session.getInstance(properties);

        try {
            // Create the email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setText(text);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // SMTP server settings
    }
        
}