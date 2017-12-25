package begin;

import java.sql.*;

public class test {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/contactdb";
        String login = "postgres";
        String password = "postgres";
        String[] str = {"Dmitry1", "Chekhov1", "+79871112231", "dmitry1@pisem.net"};

        test m = new test();

        try {
            Connection con = DriverManager.getConnection(url, login, password);
            m.testDatabase(con);
            System.out.println("contactId: " + m.update(con, str));
            m.testDatabase(con);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    private void testDatabase(Connection con) throws SQLException {

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
        while (rs.next()) {
            String str = rs.getString("contact_id") + ":" + rs.getString(2);
            System.out.println("Contact:" + str);
        }
        rs.close();
        stmt.close();

    }


    private int update (Connection con, String[] str) throws SQLException {
        int contactId = -1;
        String sql = "INSERT INTO JC_CONTACT (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(sql, new String[] {"contact_id"});
        stmt.setString(1, str[0]);
        stmt.setString(2, str[1]);
        stmt.setString(3, str[2]);
        stmt.setString(4, str[3]);
        stmt.executeUpdate();

        ResultSet gk = stmt.getGeneratedKeys();
        if (gk.next()) {
            contactId = gk.getInt("contact_id");
        }
        stmt.close();

        return contactId;
    }
}
