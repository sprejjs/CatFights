package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

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
            Class.forName(DRIVER);
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

    public boolean login(String username, String password) {
        try {
            String hashedPass = getMd5Hash(password);
            final Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String command = "SELECT * FROM Player WHERE Name = '" + username + "' AND Password = '" + hashedPass + "'";
            ResultSet rs = stmt.executeQuery(command);

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getMd5Hash(String originalString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(originalString.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
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
