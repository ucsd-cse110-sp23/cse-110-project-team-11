// import java.io.IOException;

// public class App {
//     public static void main(String[] args) throws IOException {
//         new Server();
//         LoginUI serverUI = new LoginUI();
//         serverUI.setVisible(true);
//     }
// }

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        new Server();
        //if remembered.txt exists 
        //automatically log in
        //File file = new File("remembered.txt");
        File file = new File("account.txt");
        if(file.exists()) {
            Automatic automatic = new Automatic();
        } else {
            LoginUI serverUI = new LoginUI();
            serverUI.setVisible(true);
        }

        
    }
}
