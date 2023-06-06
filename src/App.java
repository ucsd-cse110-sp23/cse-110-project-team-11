import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
       new Server();
       LoginUI serverUI = new LoginUI();
       serverUI.setVisible(true);
    }
}
