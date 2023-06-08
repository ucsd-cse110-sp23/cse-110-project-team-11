import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.EventQueue;


public class EmailErrorHandlingUI {

    public void displayError(Exception e) {
        String errorMessage = "An error occurred: " + e.getMessage();
        
        if (e instanceof AddressException) {
            errorMessage = "Invalid email address: " + e.getMessage();
        } else if (e instanceof AuthenticationFailedException) {
            errorMessage = "Authentication failed: " + e.getMessage();
        } else if (e instanceof SendFailedException) {
            errorMessage = "Failed to send email: " + e.getMessage();
        }

        final String finalErrorMessage = errorMessage;

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    finalErrorMessage,
                    "Email Error",
                    JOptionPane.ERROR_MESSAGE);
        });
    }
}
