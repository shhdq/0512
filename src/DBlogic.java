import java.sql.*;

public class DBlogic {

    // variables
    String DB = "jdbc:mysql://localhost:3306/0512";
    String USER = "root";
    String PASS = "biskviits";

    // register logic
    public void register(String email, String password) {
        try {
            Connection conn = DriverManager.getConnection(DB, USER, PASS);
//            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ps.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // login logic
    public boolean select(String email, String password) {

        boolean exists = false;

        try {
            Connection conn = DriverManager.getConnection(DB, USER, PASS);
//            Statement stmt = conn.createStatement();

            String sql = "SELECT email, password FROM users WHERE email = ? && password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                exists = true;
            }


        } catch(SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
}
