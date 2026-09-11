//  ISMAIL YILDIRIM TEAM CYPHER

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginCypher extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogOn;
    private JButton btnForgot;
    private JPanel loginPanel;

    public LoginCypher(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnLogOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
            user = getAuthenticatedUser(email, password);

            if (user != null) {
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(LoginCypher.this,
                        "Email or Password Invalid",
                        "Please Try Again",
                        JOptionPane.ERROR_MESSAGE);
            }
            }

        });
        btnForgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public User user;
    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.sortcode = resultSet.getString("sortcode");
                user.accountNumber = resultSet.getString("accountNumber");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        LoginCypher loginCypher = new LoginCypher(null);
        User user = loginCypher.user;
        if (user != null) {
            System.out.println("Succesful Authentication of : " + user.name);
            System.out.println("            Email: " + user.email);
            System.out.println("            Sort Code: " + user.sortcode);
            System.out.println("            Account Number " + user.accountNumber);
        }
        else {
            System.out.println("Authentication Cancelled");
        }
    }
}

