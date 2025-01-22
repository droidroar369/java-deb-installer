import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Class to install a DEB package through a GUI
 */
public class DebInstaller {

//Installation of program
/**
 * This method installs a DEB package with the dpkg command
 * @param pckg The path to .deb file
 * @param password The superuser password
 */
public static void install(String pckg, String password) {
  // Comamand to run
  String cmd = "sudo -S dpkg -i "+ pckg;
  String[] command = { "bash", "-c", "echo " + password + " | " + cmd};

  try {
      // Create process
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();

      // Read output of process
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      ProcessFrame fr= new ProcessFrame();
      fr.add_command_lines(cmd);
      
      String line;
      while ((line = reader.readLine()) != null) {
          fr.add_command_lines(line);
      }

      // Wait for end of process
      int exitCode = process.waitFor();
      fr.add_command_lines("Process ended with output code: " + exitCode);

  } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error while installing: I couldn't read the sequency of commands, or the process was interrupted.");
  }

}


/**
 * Method to get the path to a file, using the JFileChooser GUI
 * @return The path selected by the user
 */

public static String get_deb_path() {
  String deb_path="";

  //File chooser
  JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
  
  fc.setFileFilter(new FileNameExtensionFilter("deb packages", "deb"));
  fc.setDialogTitle("Selection of a DEB package");

  //fc.setLayout(new BorderLayout());
  //fc.add(new JLabel("Select a DEB package to install:"), BorderLayout.NORTH);

  int rv = fc.showOpenDialog(null);

  if(rv==JFileChooser.APPROVE_OPTION) {
    try { deb_path = fc.getSelectedFile().getAbsolutePath(); }
    catch(SecurityException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "I can't open this file: not enough permissions.");
    }
  } else { System.exit(0); }
  
  return deb_path;
}


/**
 * Gets a JPanel with a password field and a button, util for getting a password with the GUI
 * @return The JPanel with the password field and a helper JLabel
 */
public static JPanel get_password_panel() {
  JPanel panel= new JPanel();
  JPasswordField passwordField = new JPasswordField();
  JLabel label= new JLabel("Type superuser password:");

  panel.setLayout(new BorderLayout());
  panel.add(label, BorderLayout.CENTER);
  panel.add(passwordField, BorderLayout.SOUTH);
  return panel;
}


public static void main(String[] args) {
  //Get the .deb file
  String deb_path = DebInstaller.get_deb_path();
  
  //Get password
  JPanel panel_pswd = DebInstaller.get_password_panel();
  int option = JOptionPane.showConfirmDialog(null, panel_pswd, "Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  //If the user cancels, close the program 
  if (option != JOptionPane.OK_OPTION) {
    System.exit(0);
  }

  String password = new String( ((JPasswordField)panel_pswd.getComponent(1)).getPassword() );

  
  //System.out.println(deb_path);
  //System.out.println(password);
  DebInstaller.install(deb_path,password);

}













}
