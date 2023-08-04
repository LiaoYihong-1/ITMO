package ru.itmo.main;

import ru.itmo.classes.*;
import ru.itmo.connection.PortForwarder;
import ru.itmo.personalExceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class DataBaseManager {

    public static String collectionTableNameInDataBase;
    public static String accessTableNameInDataBase;

    static {
//        String getMusicBandByIdQuery = ; // Any useful thing after doing this way ?
        collectionTableNameInDataBase = "collection";
        accessTableNameInDataBase = "access";
    }


    public static Connection connectDataBase(String user, String password) throws SQLException {
        Connection connection;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver is not found.");
        }

        connection = DriverManager.getConnection(PortForwarder.forwardPort(), user, password);

//        if (connection != null) {
//            System.out.println("Successfully connected to database.");
//        } else {
//            throw new SQLException("Failed to connect to database");
//        }

        return connection;
    }


    /**
     * Removes MusicBand with the specified ID  form data base and adds the specified MusicBand with ID.
     * @param connection
     * @param mb MusicBand -object to add to data base.
     * @param musicBandID MusicBand ID which will have the new MusicBand.
     */
    public static void updateMusicBandByID(Connection connection, MusicBand mb, int musicBandID) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "update " + "collection" + " " + // replace with 'collectionNameInDataBase' in production
                            "set (" +
                            "mb_name, " +
                            "coord_x, " +
                            "coord_y, " +
                            "mb_creation_date, " +
                            "mb_number_of_participants, " +
                            "mb_singles_count, " +
                            "mb_genre, " +
                            "p_is_null," +
                            "p_name, " +
                            "p_height, " +
                            "p_heir_color, " +
                            "p_nationality, " +
                            "loc_x, " +
                            "loc_y, " +
                            "loc_name) = " +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                            "where mb_id = ?");

            try {
                byte i = 0;
                ps.setString(++i, mb.getName());

                ps.setDouble(++i, mb.getCoordinates().getX());
                ps.setInt(++i, mb.getCoordinates().getY());

                ps.setDate(++i, new Date(mb.getCreationDate().getTime()));
                ps.setLong(++i, mb.getNumberOfParticipants());
                ps.setInt(++i, mb.getSinglesCount());
                ps.setString(++i, mb.getGenre().toString());

                if (mb.getFrontMan() == null) {
                    ps.setBoolean(++i, true);
                    ps.setString(++i, "");
                    ps.setLong(++i, 0L);
                    ps.setString(++i, "");
                    ps.setString(++i, "");
                    ps.setInt(++i, 0);
                    ps.setInt(++i, 0);
                    ps.setString(++i, "");
                } else {
                    ps.setBoolean(++i, false);
                    ps.setString(++i, mb.getFrontMan().getName());
                    if (mb.getFrontMan().getHeight() != null) {
                        ps.setLong(++i, mb.getFrontMan().getHeight());
                    } else ps.setLong(++i, 0L);
                    if (mb.getFrontMan().getHeirColor() != null) {
                        ps.setString(++i, mb.getFrontMan().getHeirColor().toString());
                    } else ps.setString(++i, "");
                    if (mb.getFrontMan().getNationality() != null) {
                        ps.setString(++i, mb.getFrontMan().getNationality().toString());
                    } else ps.setString(++i, "");
                    ps.setInt(++i, mb.getFrontMan().getLocation().getX());
                    ps.setInt(++i, mb.getFrontMan().getLocation().getY());
                    ps.setString(++i, mb.getFrontMan().getLocation().getName());
                }

                ps.setInt(++i, musicBandID);

            } catch (SQLException e) {
                throw new DBException("Error :" + e.getMessage()); // Passing wrong data types.
            }

            ps.execute();

        } catch (SQLException e) {
            throw new DBException(
                    "Error: Connection to DB can't be established."
            );
        }
    }


    /**
     * Adds MusicBand to 'collection' table.
     * @param connection Established Connection-object to db 'studs'.
     * @param mb MusicBand -object.
     * @return MusicBand ID generated by data base.
     */
    public static int addMusicBand(Connection connection, MusicBand mb) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into " + "collection" + " " + // replace with 'collectionNameInDataBase' in production
                            "(" +
                            "mb_name, " +
                            "coord_x, " +
                            "coord_y, " +
                            "mb_creation_date, " +
                            "mb_number_of_participants, " +
                            "mb_singles_count, " +
                            "mb_genre, " +
                            "p_is_null, " +
                            "p_name, " +
                            "p_height, " +
                            "p_heir_color, " +
                            "p_nationality, " +
                            "loc_x, " +
                            "loc_y, " +
                            "loc_name) " +
                            "values " +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                            "returning mb_id;");
            try {
                byte i = 0;
                ps.setString(++i, mb.getName());

                ps.setDouble(++i, mb.getCoordinates().getX());
                ps.setInt(++i, mb.getCoordinates().getY());

                ps.setDate(++i, new Date(mb.getCreationDate().getTime()));
                ps.setLong(++i, mb.getNumberOfParticipants());
                ps.setInt(++i, mb.getSinglesCount());
                ps.setString(++i, mb.getGenre().toString());

                if (mb.getFrontMan() == null) {
                    ps.setBoolean(++i, true);
                    ps.setString(++i, "");
                    ps.setLong(++i, 0L);
                    ps.setString(++i, "");
                    ps.setString(++i, "");
                    ps.setInt(++i, 0);
                    ps.setInt(++i, 0);
                    ps.setString(++i, "");
                } else {
                    ps.setBoolean(++i, false);
                    ps.setString(++i, mb.getFrontMan().getName());
                    if (mb.getFrontMan().getHeight() != null) {
                        ps.setLong(++i, mb.getFrontMan().getHeight());
                    } else ps.setLong(++i, 0L);
                    if (mb.getFrontMan().getHeirColor() != null) {
                        ps.setString(++i, mb.getFrontMan().getHeirColor().toString());
                    } else ps.setString(++i, "");
                    if (mb.getFrontMan().getNationality() != null) {
                        ps.setString(++i, mb.getFrontMan().getNationality().toString());
                    } else ps.setString(++i, "");
                    ps.setInt(++i, mb.getFrontMan().getLocation().getX());
                    ps.setInt(++i, mb.getFrontMan().getLocation().getY());
                    ps.setString(++i, mb.getFrontMan().getLocation().getName());
                }
            } catch (SQLException e) {
                throw new DBException("Error :" + e.getMessage()); // Passing wrong data types.
            }

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("mb_id");

        } catch (SQLException e) {
            throw new DBException(String.format(
                    "Error: MusicBand with name : '%s' already exists." +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    mb.getName()
            ));
        }
    }


    public static TreeMap<Integer, MusicBand> loadCollectionFromDataBase(Connection connection) throws SQLException {

        try {
            TreeMap<Integer, MusicBand> collection = new TreeMap<>();

            ResultSet resultSet = connection.prepareStatement("select * from collection").executeQuery();
            MusicBand musicBand;

            while (resultSet.next()) {
                musicBand = getMusicBandFromResultSet(resultSet);
                collection.put(musicBand.getId(), musicBand);
            }

            return collection;

        } catch (SQLException e) {
            throw new DBException(
                    "Error: Connection to DB can't be established."
            );
        }
    }


    /**
     * Extracts MusicBand data from ResultSet to MusicBand -object.
     * @param resultSet ResultSet -object from request to db 'collection'.
     * @return MusicBand.
     */
    private static MusicBand getMusicBandFromResultSet(ResultSet resultSet) {

        try {
            MusicBand musicBand = new MusicBand();
            musicBand.setId(resultSet.getInt("mb_id"));
            musicBand.setName(resultSet.getString("mb_name"));
            musicBand.setCreationDate(new java.util.Date(resultSet.getDate("mb_creation_date").getTime()));
            musicBand.setNumberOfParticipants(resultSet.getLong("mb_number_of_participants"));
            musicBand.setSinglesCount(resultSet.getInt("mb_singles_count"));
            musicBand.setGenre(MusicGenre.valueOf(resultSet.getString("mb_genre").toUpperCase()));


            Coordinates coordinates = new Coordinates();
            coordinates.setX(resultSet.getDouble("coord_x"));
            coordinates.setY(resultSet.getInt("coord_y"));

            musicBand.setCoordinates(coordinates);


            Person person = new Person();
            if (resultSet.getBoolean("p_is_null")) {
                person = null;

            } else {
                person.setName(resultSet.getString("p_name"));
                if (resultSet.getLong("p_height") != 0L) {
                    person.setHeight(resultSet.getLong("p_height"));
                } else person.setHeight(null);
                if ( ! resultSet.getString("p_heir_color").equals("")) {
                    person.setHeirColor(Color.valueOf(resultSet.getString("p_heir_color").toUpperCase()));
                } else person.setHeirColor(null);
                if ( ! resultSet.getString("p_nationality").equals("")) {
                    person.setNationality(Country.valueOf(resultSet.getString("p_nationality")));
                } else person.setNationality(null);

                Location location = new Location();
                location.setX(resultSet.getInt("loc_x"));
                location.setY(resultSet.getInt("loc_y"));
                location.setName(resultSet.getString("loc_name"));

                person.setLocation(location);
            }


            musicBand.setFrontMan(person);

            return musicBand;

        } catch (SQLException e) {
            throw new DBException("Error: " + e.getMessage()); // There is no column in the table which name we request.
        }
    }


    /**
     *
     * @param connection Established Connection-object to db 'studs'.
     * @param musicBandID MusicBand ID to get MusicBand by.
     * @return MusicBand from 'collection' table.
     */
    public static MusicBand getMusicBandByID(Connection connection, int musicBandID) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from collection where mb_id = ?"
            );

            ps.setInt(1, musicBandID);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new DBException(String.format(
                        "Error: The MusicBand with ID : '%s' doesn't exist.",
                        musicBandID));
            }

            return getMusicBandFromResultSet(rs);

        } catch (SQLException e) {
            throw new DBException(
                    "Error: Connection to DB can't be established."
            );
        }
    }



    /**
     * Returns MusicBands IDs owned by user with the specified User -object.
     * @param connection Established Connection-object to db 'studs'.
     * @param user User ID in 'access' table.
     * @return List of owned MusicBands.
     */
    public static ArrayList<Integer> getOwnedMusicBandsByUser(Connection connection, User user) {
        return getOwnedMusicBandsByUser(connection, DataBaseManager.getUserIDByUserName(connection, user.getLogin()));
    }


    /**
     * Returns MusicBands IDs owned by user with the specified ID.
     * @param connection Established Connection-object to db 'studs'.
     * @param userID User ID in 'access' table.
     * @return List of owned MusicBands.
     */
    public static ArrayList<Integer> getOwnedMusicBandsByUser(Connection connection, int userID) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select owned_elements_ids from access where user_id = ?"
            );

            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new DBException(String.format(
                        "Error: No user with ID : '%s'",
                        userID));
            }

            ArrayList<Integer> a = new ArrayList<>(Arrays.asList((Integer[]) rs.getArray("owned_elements_ids").getArray()));

            if (a == null) {
                return new ArrayList<>(0); //The user doesn't own any MusicBands.
            } else return a;

        } catch (SQLException e) {
            throw new DBException(
                    "Error: Connection to DB can't be established.");
        }
    }


    /**
     * Adds new MusicBand ID to 'access' table to corresponding user by user ID.
     * @param connection Established Connection-object to db 'studs'.
     * @param userID User ID in 'users' table.
     * @param musicBandID MusicBand ID.
     */
    public static void addOwnedMusicBandIDByUserID(Connection connection, int userID, int musicBandID) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "update access set owned_elements_ids = array_append(owned_elements_ids, ?) where user_id = ?"
            );

            ps.setInt(1, musicBandID);
            ps.setInt(2, userID);

            ps.execute();
        } catch (SQLException e) {
            throw new DBException(String.format(
                    "Error: Can't add owned musicBand ID : '%s' to user with ID : '%s'." +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    musicBandID, userID));
        }
    }


    /**
     * Adds new user ID to 'access' table.
     * @param connection Established Connection-object to db 'studs'.
     * @param userID User ID in the table 'users'.
     */
    public static void addUserToAccessTable(Connection connection, int userID) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into access (user_id) values (?)"
            );

            ps.setInt(1, userID);
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(String.format(
                    "Error: User ID : '%s' already exists." +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    userID));
        }
    }


    /**
     * Adds new user to 'users' table.
     * @param connection Established Connection-object to db 'studs'.
     * @param userName User name in 'users' table.
     * @param userPass User password in 'users' table.
     * @return User ID generated by data base.
     */
    public static int addUser(Connection connection, String userName, String userPass) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into users (" +
                            "user_name, " +
                            "user_pass" +
                            ") " +
                            "values (?, ?)" +
                            "returning user_id"
            );

            ps.setString(1, userName);
            ps.setString(2, userPass);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("user_id");

        } catch (SQLException e) {
            throw new DBException(String.format(
                    "\nError: User name : '%s' already exists." +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    userName));
        }
    }


    /**
     * Adds new user to 'users' table.
     * @param connection Established Connection-object to db 'studs'.
     * @param user User -object.
     * @return User ID generated by data base.
     */
    public static int addUser(Connection connection, User user) {
        return addUser(connection, user.getLogin(), user.getPassword());
    }


    /**
     * Returns the User object taken from 'users' table by user name.
     * @param connection Established Connection-object to db 'studs'.
     * @param userName User name in 'users' table.
     * @return User object
     */
    public static User getUserByUserName(Connection connection, String userName) {
        try {
            User user = new User();

            PreparedStatement ps = connection.prepareStatement(
                    "select * from users where user_name = ?"
            );

            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;
            user.setLogin(userName);
            user.setPassword(rs.getString("user_pass").trim());

            return user;

        } catch (SQLException e) {
            throw new DBException(String.format(
                    "\nError: No user with Name : '%s' " +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    userName));
        }
    }


    /**
     * Returns user's ID taken from 'users' table by user name.
     * @param connection  Established Connection-object to db 'studs'.
     * @param userName  User name in 'users' table.
     * @return User ID.
     */
    public static int getUserIDByUserName(Connection connection, String userName) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select user_id from users where user_name = ?"
            );

            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return -1;
            return rs.getInt("user_id");
        } catch (SQLException e) {
            throw new DBException(String.format(
                    "\nError: No user with Name : '%s' " +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    userName));
        }
    }


    public static void clearCollectionTable(Connection connection) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "delete from collection"
            );
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(
                    "Connection to DB can't be established.");
        }
    }


    /**
     * Removes MusicBand by its 'ID' from 'collection' table.
     * @param connection
     * @param musicBand
     */
    public static void removeMusicBand(Connection connection, MusicBand musicBand) {
        removeMusicBandByID(connection, musicBand.getId());
    }


    public static void removeMusicBandByID(Connection connection, int musicBandID) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "delete from collection where mb_id = ?"
            );

            ps.setInt(1, musicBandID);
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(String.format(
                    "Error: There is no musicBand with ID : '%s' in the 'collection table'." +
                    "\n__or__" +
                    "\nConnection to DB can't be established.",
                    musicBandID));
        }
    }


    public static void removeOwnedMusicBand(Connection connection, User user, int musicBandID) {
        removeOwnedMusicBand(connection, DataBaseManager.getUserIDByUserName(connection, user.getLogin()), musicBandID);
    }


    public static void removeOwnedMusicBand(Connection connection, int userID, int musicBandID) {
        try {
            ArrayList<Integer> arrayList = getOwnedMusicBandsByUser(connection, userID);
            arrayList.remove(arrayList.indexOf(musicBandID));

            PreparedStatement ps = connection.prepareStatement(
                    "update access set owned_elements_ids =  ? where user_id = ?"
            );

            ps.setArray(1, connection.createArrayOf("integer", arrayList.toArray(new Integer[0])));
            ps.setInt(2, userID);

            ps.execute();
        } catch (SQLException e) {
            throw new DBException(String.format(
                            "Error: No user with ID : '' in the 'access' table." +
                            "\n__or__" +
                            "\nConnection to DB can't be established.",
                    userID));
        }
    }


    public static boolean userOwnsMusicBand(Connection connection, User user, int musicBandID) {

        return  (DataBaseManager.getOwnedMusicBandsByUser(connection, user).contains(musicBandID));
    }


    public static boolean userOwnsMusicBand(Connection connection, User user, MusicBand musicBand) {

        return  (DataBaseManager.userOwnsMusicBand(connection, user, musicBand.getId()));
    }

}
