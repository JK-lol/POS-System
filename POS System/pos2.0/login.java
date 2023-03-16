/*  
Aggregation : Line 29 (Main method inside the class that referring to itself)

Use of interface: Line 14 (Implements Action Listener) (All the actions is specified at starting from line 104)
 
File & exception handling: Line 112 (Start to compare the user input with file to make sure username and password entered is correct)
!! We are not letting anyone accessing the file throughout the interface as we dont want any suspicous person to handle the pos system
*/
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.*;

public class login implements ActionListener
{
   private static JLabel user_label;
   private static JTextField username_input;
   private static JLabel password_label;
   private static JTextField password_input;
   private static JButton login_button;
   private static JButton cancel_button;
   private static JLabel success;
   private static JLabel failure;
   private static JButton gotoPOS;
   private static JFrame login_frame;
   private static JButton gotoitem;
   private static JButton gotosales; 

   public static void main(String[] args) {
    new login();
   }


    public login() {
    login_frame = new JFrame();
    JPanel login_panel = new JPanel();
    login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    login_frame.setTitle("Login Page");

    login_frame.setSize(295,350);
    login_frame.add(login_panel);

    login_panel.setLayout(null);
    login_frame.setLocationRelativeTo(null);

    user_label = new JLabel("User: ");
    user_label.setBounds(10,20,80,25);
    login_panel.add(user_label);

    username_input = new JTextField(20);
    username_input.setBounds(100,20,165,25);
    login_panel.add(username_input);

    password_label = new JLabel("Password: ");
    password_label.setBounds(10,50,80,25);
    login_panel.add(password_label);

    password_input = new JPasswordField(20); // to hide the password input
    password_input.setBounds(100,50,165,25);
    login_panel.add(password_input);

    login_button = new JButton("Login");
    login_button.setBounds(10,110,125,25);
    login_button.addActionListener(this);
    login_panel.add(login_button);

    cancel_button = new JButton("Cancel");
    cancel_button.setBounds(150,110,125,25);
    cancel_button.addActionListener(this);
    login_panel.add(cancel_button);

    success = new JLabel("");
    success.setBounds(10,150,275,25);
    login_panel.add(success);

    failure = new JLabel("");
    failure.setBounds(10,150,265,25);
    login_panel.add(failure);


    gotoPOS = new JButton("Bill");
    gotoPOS.setBounds(10,180,265,25);
    gotoPOS.addActionListener(this);
    gotoPOS.setVisible(false);
    login_panel.add(gotoPOS);

    gotoitem = new JButton("Manage item");
    gotoitem.setBounds(10,220,265,25);
    gotoitem.addActionListener(this);
    gotoitem.setVisible(false);
    login_panel.add(gotoitem);

    gotosales = new JButton("Sales Report");
    gotosales.setBounds(10,260,265,25);
    gotosales.addActionListener(this);
    gotosales.setVisible(false);
    login_panel.add(gotosales);


    login_frame.setVisible(true);

   }

   public void actionPerformed (ActionEvent e)
   {
        if (e.getSource()==login_button)
        {
            String user = username_input.getText();
            String password = password_input.getText();

            //start to compare user input with txt file
            try 
            {
                BufferedReader reader = new BufferedReader(new FileReader("user.txt"));
                String line;
                while ((line=reader.readLine())!=null)
                {
                    String [] credentials = line.split(":");
                    String file_user = credentials[0];
                    String file_password = credentials[1];
            

                if(user.equals(file_user) && password.equals(file_password))
                {
                    
                    failure.setText("");//as an insurance so that failure text wont cover successful text
                    success.setText("Login successful!");
                    gotoPOS.setVisible(true);
                    gotoitem.setVisible(true);
                    gotosales.setVisible(true);
                    reader.close();

                }
                else
                {
                    success.setText("");
                    failure.setText("Wrong username or password, please try again");
                    gotoPOS.setVisible(false);
                    gotoitem.setVisible(false);
                    gotosales.setVisible(false);
                }
                
                }
                
            } 
            catch (IOException ex) {
            
                ex.printStackTrace();
              }


        }

        else if (e.getSource()==gotoPOS)
        {
            new pay_item();
            login_frame.dispose();
        }

        else if (e.getSource()==gotoitem)
        {
            new item();
            login_frame.dispose();
        }
        else if (e.getSource()==gotosales)
        {
            new sales_report();
            login_frame.dispose();
        }


        else if (e.getSource()==cancel_button)
        {
            System.exit(0);
        }

        
   }

}