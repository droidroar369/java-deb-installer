import javax.swing.*;
import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

/**
 * Class to build a frame terminal for showing the results of executing a command
 */
public class ProcessFrame extends JFrame {
    
    /**
     * The command terminal, a non-editable JTextPane that shows the outputs of the console
     */
    private JTextPane terminal;
    

    /**
     * Constructs and configures a JFrame, then it adds the "terminal" JTextPane
     */
    public ProcessFrame() {
        //Configure window
        super();
        setLayout(null);
        setTitle("Installing...");
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        terminal= new JTextPane();
        terminal.setContentType("text/html");
        terminal.setText("<html>\n"+
                     "<body style='background-color:black; color: white;'>\n"+
                     //"<p>example</p>\n"+
                     "</body>\n"+
                     "</html>");
        terminal.setEditable(false);
        
        //Scroll for the text-area
        JScrollPane scrollPane = new JScrollPane(terminal);
        
        //Test button
        /*JButton b=new JButton("Pica");
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                add_command_lines("hola");
            }
        });
        JPanel bpanel = new JPanel();
        bpanel.setLayout(new BorderLayout());
        bpanel.add(b, BorderLayout.CENTER);*/

        //Put elements in the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //getContentPane().add(bpanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }




/**
 * Adds a new line in the "terminal" area.
 * @param text The new line to put at the end of the "terminal"
 */
public void add_command_lines(String text) {
    //Get current text of the pane
    String text_content= terminal.getText();

    //The content is an HTML, so, replace the 2 last lines with a new line with the text, and close the HTML with </body> and </html>
    String new_text= text_content.replaceAll("(?s)(.*)(black)(.*)(\\R.*\\R.*\\R.*)", "$1"+ "$2"+ "; color:white"+ "$3"+ "\n<p>"+ text+ "</p>\n"+ "</body>\n</html>");
    //Set the text
    terminal.setText(new_text);
    //System.out.println(new_text);
}


//public static void main(String[] args) {
//    ProcessFrame frame= new ProcessFrame();
//}


}