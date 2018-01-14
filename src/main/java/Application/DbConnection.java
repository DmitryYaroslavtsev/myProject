package Application;

import com.mchange.v2.c3p0.*;
import java.sql.*;

public class DbConnection {

    private static String urlUsers = "jdbc:postgresql://localhost:5432/myProject";
    private static String login = "postgres";
    private static String pass = "postgres";
    private static String driver = "org.postgresql.Driver";

    private ComboPooledDataSource dataSource;

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

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
