import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        // init objects
        JFrame frame = new JFrame("Login/ Register");
        JPanel entry = new JPanel();
        JPanel dashboard = new JPanel();

        // layout reset
        entry.setLayout(null);
        dashboard.setLayout(null);

        // dashboard
        JLabel welcome = new JLabel();
        welcome.setBounds(30, 50, 110, 20);
        dashboard.add(welcome);

        // logout button
        JButton logout = new JButton("Logout");
        logout.setBounds(25, 75, 110, 30);
        dashboard.add(logout);

        // frame
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(entry);

        // error
        JLabel error = new JLabel();
        error.setBounds(100, 20, 120, 20);
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

        // register button
        JButton register = new JButton("Register");
        register.setBounds(100, 110, 140, 40);
        entry.add(register);

        // login button
        JButton login = new JButton("Login");
        login.setBounds(100, 150, 140, 40);
        entry.add(login);

        // register
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

                        db.register(newEmail, newPassword);
                    }
                }
            }
        });

        // login
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(emailField.getDocument().getLength() == 0 || passwordField.getDocument().getLength() == 0) {
                    error.setText("Empty fields");
                } else {
                    error.setText("");

                    DBlogic db = new DBlogic();

                    String newEmail = emailField.getText();
                    String newPassword = String.valueOf(passwordField.getPassword());

                    if(db.select(newEmail, newPassword)) {
                        frame.getContentPane().remove(entry);
                        frame.add(dashboard);
                        welcome.setText("Welcome " + newEmail);
                        frame.setTitle("Dashboard");
                        frame.getContentPane().invalidate();
                        frame.getContentPane().validate();
                        frame.getContentPane().repaint();

                    } else {
                        // error
                        error.setText("Invalid credentials");
                    }

                }
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(dashboard);
                frame.add(entry);
                frame.setTitle("Login/ Register");
                frame.getContentPane().invalidate();
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
            }
        });
        
        frame.setVisible(true);

    }
    // end of main method
}
// end of main class