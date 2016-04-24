package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vspreys on 25/04/16.
 */
public class DBHelper {

    private static DBHelper instance = null;
    private Connection connection = null;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:8889/cat_fights";
    private static final String DB_USERNAME = "cat_fights";
    private static final String DB_PASSWORD = "pDT7vCx6Z4pVrp6N";

    private DBHelper() {
        System.out.println("Trying to access mysql db");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Signleton design pattern.
     * @return creates a new instance of DBHelper if that doesn't exist yet and then returns the instance
     */
    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }
}
