import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        new Server();
        File file = new File("remembered.txt");
        if(file.exists()) {
            Automatic automatic = new Automatic();
        } else {
            LoginUI serverUI = new LoginUI();
            serverUI.setVisible(true);
        }
    }
}
