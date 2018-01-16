package Application;

import com.mchange.v2.c3p0.*;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

    private static String urlUsers = "jdbc:postgresql://localhost:5432/myProject";
    private static String login = "postgres";
    private static String pass = "postgres";
    private static String driver = "org.postgresql.Driver";

    private static ComboPooledDataSource dataSource;
    private static DbConnection connection = new DbConnection();

    public DbConnection() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(urlUsers);
            dataSource.setUser(login);
            dataSource.setPassword(pass);
            dataSource.setMaxPoolSize(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    static ArrayList fillPerson() {
        ArrayList ll = new ArrayList();
        String[] str = new String[5];
        try {
            try (Connection con = connection.getConnection()) {
                ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM jc_contact");
                while (resultSet.next()) {
                    for (int i = 0; i < 5; i++) {
                        str[i] = resultSet.getString(i + 1);
                    }
                    ll.add(new Person(str[0], str[1], str[2], str[3], str[4]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ll;
    }

    //Verification of user during login to App
    static boolean checkUser(String username, String password) {
        boolean check = false;
        if (!username.contains(";") & !username.contains("'")) {
            try {
                try (Connection con = getConnection()){
                    ResultSet resultSet = con.createStatement().
                            executeQuery("SELECT * FROM users WHERE username like '" + username + "'");
                    while (resultSet.next()) {
                        if (resultSet.getString("username").equals(username) &&
                                resultSet.getString("password").equals(password)) {
                            check = true;
                    }
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    static void updateTable(String firstName, String lastName, String phone, String email) {

        if (App.nonSqlInjection(firstName) && App.nonSqlInjection(lastName) &&
                App.nonSqlInjection(phone) && App.nonSqlInjection(email)) {
            try {
                try (Connection con = getConnection()) {
                    PreparedStatement stmt = con.prepareStatement("INSERT INTO JC_CONTACT (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES (?, ?, ?, ?)");
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setString(3, phone);
                    stmt.setString(4, email);
                    stmt.executeUpdate();
                    stmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
