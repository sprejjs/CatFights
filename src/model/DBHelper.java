package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Player login(String username, String password) {
        try {
            String hashedPass = getMd5Hash(password);
            final Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String command = "SELECT * FROM Player WHERE Name = '" + username + "' AND Password = '" + hashedPass + "'";
            ResultSet rs = stmt.executeQuery(command);

            rs.first();

            int id = rs.getInt("id");

            return new Player(id, username);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Player register(String username, String password) {
        try {
            String command = "INSERT INTO Player (`id`, `Name`, `Password`) VALUES (NULL, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, getMd5Hash(password));
            stmt.executeUpdate();

            return login(username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Cat> getCats() {
        List<Cat> cats = new ArrayList<>();
        try {
            final Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String command = "SELECT * from Cat";
            ResultSet rs = stmt.executeQuery(command);
            rs.first();

            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String photoPath = rs.getString("photoPath");
                cats.add(new Cat(id, name, photoPath));
            }
            while (rs.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cats;
    }

    public List<GameResult> getGameResults() {
        List<GameResult> gameResults = new ArrayList<>();
        try {
            final Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String command = "SELECT Cat.id AS 'CatId', Cat.name AS 'CatName', Cat.photoPath,  " +
                    "Player.id AS 'PlayerId', Player.Name AS 'PlayerName', " +
                    "GameResult.date " +
                    "FROM GameResult " +
                    "JOIN(Cat, Player) " +
                    "ON (GameResult.winnerId = Cat.id AND GameResult.playerId = Player.id) " +
                    "ORDER BY date DESC";

            ResultSet rs = stmt.executeQuery(command);
            rs.first();

            do {
                //Retrieve the cat details
                int catId = rs.getInt("CatId");
                String catName = rs.getString("CatName");
                String photoPath = rs.getString("photoPath");
                Cat winningCat = new Cat(catId, catName, photoPath);

                //Retrieve player details
                int playerId = rs.getInt("PlayerId");
                String playerName = rs.getString("PlayerName");
                Player player = new Player(playerId, playerName);

                //Retrieve the date
                String dateAsString = rs.getString("date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(dateAsString);

                //Retrieve the player details
                gameResults.add(new GameResult(player, winningCat, date));
            }
            while (rs.next());

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return gameResults;
    }

    public void saveGameResults(GameResult result) {
        try {
            final Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String command = "INSERT INTO `cat_fights`.`GameResult` (`id`, `winnerId`, `playerId`, `date`) " +
                    "VALUES (NULL, '" + result.getWinnerId() + "', '" + result.getPlayerId() + "', '" + result.getDateAsString() + "');";

            stmt.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
     *
     * @return creates a new instance of DBHelper if that doesn't exist yet and then returns the instance
     */
    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }
}
