package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Parker on 2/26/18.
 */

public class DAO {

    Connection connection = null;

    DAO()
    {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }

    public void openConnection()
    {
        String dbName = "FMDB.db";
        String connectionURL = "jdbc:sqlite:" + dbName;

        try {
            // Open a database connection
            connection = DriverManager.getConnection(connectionURL);
            // Start a transaction
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                connection.commit();
            }
            else {
                connection.rollback();
            }

            connection.close();
            connection = null;
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }


}
