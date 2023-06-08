// import javax.swing.*;
// import java.awt.*;

// class clearAllButton extends JPanel{
    
//     private JButton clearAll;
//     //private JTextArea answerArea;

//     clearAllButton(){        
//         setPreferredSize(new Dimension(400, 60));
//         setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

//         // Create the button
//         clearAll = new JButton("Clear All");
//         clearAll.setPreferredSize(new Dimension(100, 60)); // set the size of the button
//         clearAll.setFont(new Font("Sans-serif", Font.ITALIC, 10));
//         clearAll.setBorder(BorderFactory.createEmptyBorder());

//         //adding to the panel
//         this.add(clearAll);

//         clearAll.addActionListener(e -> clearAllQuestions());
//     }

//     public JButton getClear(){
//         return clearAll;
//     }
    
//     public void clearAllQuestions(){
//         try{
//             //assuring the file exists
//             JsonStorage prompt = new JsonStorage("historyPrompt.json");
//             //method to clear the JsonStorage object wihich contains both questions/ answers
//             prompt.clearPrompt();
//         }catch(Exception e){
//             System.out.println("Invalid File");
//         }
//         this.repaint();


//     }

// }
