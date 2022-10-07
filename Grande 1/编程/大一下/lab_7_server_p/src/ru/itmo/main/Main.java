package ru.itmo.main;

import ru.itmo.classes.*;
import ru.itmo.connection.*;
import ru.itmo.personalExceptions.DBException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    static int numberOfRequestsToSaveTheCollection;
    static String filePath;
    static Connection connection;
    static final String USER = "s284704";
    static final String PASS = "hxy284";

    public static void main(String[] args) throws IOException, SQLException {



//        String filePath = System.getenv("COLLECTION_PATH");
//        if (filePath == null) {
//            System.out.println("Error: Env var 'COLLECTION_PATH' doesn't exist.");
//            return;
//        }
//        filePath = "collection.json";

//
//
//        FileManager fileManager;
//        TreeMap<Integer, MusicBand> collectionFromFile = new TreeMap<>();




//            fileManager = new FileManager();
//            collection = fileManager.getCollectionFromFile(filePath);
//            CommandManager.setFileManager(fileManager); //Is needed only for printing collection's DateOfInitialisation when running command "info
//            CommandManager.setFilePath(filePath);


        TreeMap<Integer, MusicBand> collection;

        try {
            connection = DataBaseManager.connectDataBase(USER, PASS);
            collection = DataBaseManager.loadCollectionFromDataBase(connection);
            System.out.println("Collection was loaded successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }





        //--------------------------------------------


//        MusicBand musicBand = new MusicBand(1, "Beatles", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, new Person("John Lennon", 195L, Color.RED, Country.GERMANY, new Location(1, 2, "Scene")));
//        MusicBand musicBand2 = new MusicBand(1, "HritlesP", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, null);
//        User user = new User("maxi", "30981ab525a917193e15ed1e632f409ad5dd237c3cd9f4df65cd2a9e");
//
//        InsertCommand.execute(collectionFromFile, connection, user, musicBand2);
//        DataBaseManager.addMusicBandToCollectionTable(connection, musicBand2);
//            System.out.println(DataBaseManager.getMusicBandByIDFromCollectionTable(connection, 9));

//        System.out.println(DataBaseManager.getMusicBandByID(connection, 99));

        ConnectionManager connectionManager = new ConnectionManager(9100);
        while (true) {
            runCommand(collection, connectionManager, connection);
        }

    }


    public static void runCommand(TreeMap<Integer, MusicBand> collection, ConnectionManager connectionManager, Connection connection) {

        CommandRequest commandRequest;
        CommandResponse commandResponse = new CommandResponse();
        ServiceRequest serviceRequest;
        ServiceResponse serviceResponse = new ServiceResponse();
        Object requestObject;

        try {
            requestObject = Serializer.toObject(connectionManager.receive());
            System.out.println("I ve got rqObject.");

            if (requestObject instanceof CommandRequest) {
                commandRequest = (CommandRequest) requestObject;
                commandResponse = CommandManager.executeCommand(collection, commandRequest, connection);
                connectionManager.send(Serializer.toByteArray(commandResponse));


            } else if (requestObject instanceof ServiceRequest) {
                serviceRequest = (ServiceRequest) requestObject;
                try {
                    serviceResponse = executeServiceRequest(connection, serviceRequest);

                } catch (DBException e) {
                    serviceResponse.setErrorMessage(e.getMessage());
                    System.out.println(e.getMessage());
                }

                connectionManager.send(Serializer.toByteArray(serviceResponse));
            }


        } catch (IOException | ClassNotFoundException e) { // Exception e  --make later
            e.printStackTrace();
            commandResponse.setMessage("Error: Command wasn't executed. Reason: 'ServerError'.");
            try {
                connectionManager.send(Serializer.toByteArray(commandResponse));
            }catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }

        }
    }



    public static ServiceResponse executeServiceRequest(Connection connection, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();

        if (serviceRequest.isRegistration()) {
            try {
                int id = DataBaseManager.addUser(connection, serviceRequest.getUser());
                DataBaseManager.addUserToAccessTable(connection, id);
                serviceResponse.setRegistered(true);

            } catch (DBException e) {
                serviceResponse.setErrorMessage(e.getMessage());
            }

        } else if (serviceRequest.isAuthorisation()) {
            User userFromDB;
            try {
                userFromDB = DataBaseManager.getUserByUserName(connection, serviceRequest.getUser().getLogin());
            } catch (DBException e) {
                serviceResponse.setErrorMessage(e.getMessage());
                return serviceResponse;
            }


            if (userFromDB == null) {
                serviceResponse.setErrorMessage("Error: No user with the specified user name.");
            } else if ( !serviceRequest.getUser().getPassword().equals(userFromDB.getPassword())) {
                serviceResponse.setErrorMessage("Error: Wrong password.");
            } else {
                serviceResponse.setAuthorised(true);
            }

        }

        return serviceResponse;
    }













    public static void checkCollectionForKeysIDsIdentity(TreeMap<Integer, MusicBand> collection) {
        for (Map.Entry<Integer, MusicBand> entry: collection.entrySet()) {
            if (!entry.getKey().equals(entry.getValue().getId())) {
                throw new IllegalArgumentException(String.format("Error: Key isn't identical to ID. Element: key: '%s', id: '%s'", entry.getKey(), entry.getValue().getId()));
            }
        }
    }


    public static void checkCollectionForFieldsValues(TreeMap<Integer, MusicBand> collection) {
        for (Integer key: collection.keySet()) {
            try {
                MusicBand musicBand = new MusicBand();
                Person person = new Person();
                Coordinates coordinates = new Coordinates();
                Location location = new Location();

                MusicBand musicBandParsed = collection.get(key);
                Coordinates coordinatesParsed = musicBandParsed.getCoordinates();
                Person personParsed = musicBandParsed.getFrontMan();

                if (personParsed == null) person = null;
                else {
                    Location locationParsed = personParsed.getLocation();
                    location.setX(locationParsed.getX());
                    location.setY(locationParsed.getY());
                    location.setName(locationParsed.getName());

                    person.setName(personParsed.getName());
                    person.setHeight(personParsed.getHeight());
                    person.setHeirColor(personParsed.getHeirColor());
                    person.setNationality(personParsed.getNationality());
                    person.setLocation(location);
                }

                coordinates.setX(coordinatesParsed.getX());
                coordinates.setY(coordinatesParsed.getY());

                musicBand.setId(musicBandParsed.getId());
                musicBand.setName(musicBandParsed.getName());
                musicBand.setCoordinates(coordinates);
                musicBand.setNumberOfParticipants(musicBandParsed.getNumberOfParticipants());
                musicBand.setSinglesCount(musicBandParsed.getSinglesCount());
                musicBand.setGenre(musicBandParsed.getGenre());
                musicBand.setFrontMan(person);


            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(String.format("Error: Element with key: '%s'\n%s", key, e.getMessage()));
            }
        }
    }

    // For testing the Method "checkCollectionForFieldsValues" productivity
    //    checkCollectionForFieldsValues(generateHugeCollection(900000));
//            System.out.println("(Java) : I'm ready!");
    public static TreeMap<Integer, MusicBand> generateHugeCollection(Integer numberOfBands) {
        TreeMap<Integer, MusicBand> collection = new TreeMap<>();

        for (int i = 0; i < numberOfBands; i++) {
            MusicBand beatles = new MusicBand(i, "Beatles", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, new Person("John Lennon", 195L, Color.RED, Country.GERMANY, new Location(1, 2, "Scene")));
            collection.put(i, beatles);
        }
        return collection;
    }


}


