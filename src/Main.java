import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Login/ Register");
        JPanel entry = new JPanel();
        JPanel dashboard = new JPanel();

        entry.setLayout(null);

        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(entry);

        // error
        JLabel error = new JLabel();
        error.setBounds(100, 20, 100, 20);
        error.setForeground(Color.RED);
        entry.add(error);

        // email label
        JLabel email = new JLabel("Email: ");
        email.setBounds(35, 50, 80, 20);
        entry.add(email);

        // email input
        JTextField emailField = new JTextField();
        emailField.setBounds(100, 50, 140, 20);
        entry.add(emailField);

        // password label
        JLabel password = new JLabel("Password: ");
        password.setBounds(35, 85, 80, 20);
        entry.add(password);

        // password input
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 85, 140, 20);
        entry.add(passwordField);

        JButton register = new JButton("Register");
        register.setBounds(100, 110, 140, 40);
        entry.add(register);

        JButton login = new JButton("Login");
        login.setBounds(100, 150, 140, 40);
        entry.add(login);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(emailField.getDocument().getLength() == 0 || passwordField.getDocument().getLength() == 0) {
                    error.setText("Empty fields");
                } else {
                    error.setText("");

                    if(passwordField.getDocument().getLength() <= 8) {
                        System.out.println("Error");
                    } else {
                        // final
                        DBlogic db = new DBlogic();

                        String newEmail = emailField.getText();
                        String newPassword = String.valueOf(passwordField.getPassword());


                        // convert to String
//                        String newEmail = (String).toString();
//                        String newPassword = passwordField.toString();

                        db.register(newEmail, newPassword);
                    }
                }


            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(emailField.getDocument().getLength() == 0 || passwordField.getDocument().getLength() == 0) {
                    error.setText("Empty fields");
                } else {
                    error.setText("");

                    frame.getContentPane().remove(entry);
                    frame.add(dashboard);
                    frame.setTitle("Dashbaord");
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();

                }


            }
        });

        frame.setVisible(true);

    }
}