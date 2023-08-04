package ru.itmo.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class MusicBand implements Comparable<MusicBand>, Serializable {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private long numberOfParticipants;
    private int singlesCount;
    private MusicGenre genre;
    private Person frontMan;

    public static HashMap<String, String> musicBandFieldsDescription;

    public MusicBand(Integer id,
                     String name, //To deal with is field !
                     Coordinates coordinates,
                     long numberOfParticipants,
                     int singlesCount,
                     MusicGenre genre,
                     Person frontMan) {
        this.setId(id);
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate();
        this.setNumberOfParticipants(numberOfParticipants);
        this.setSinglesCount(singlesCount);
        this.setGenre(genre);
        this.setFrontMan(frontMan);
    }


    public MusicBand(){
        this.setCreationDate();
    }


    static {
        musicBandFieldsDescription = new HashMap<>();
        musicBandFieldsDescription.put("name",                 "Field band 'name' {String} can't be null or empty.");
        musicBandFieldsDescription.put("coordinates",          "Field band 'coordinates' {Coordinates} can't be null.");
        musicBandFieldsDescription.put("numberOfParticipants", "Field 'number of participants' {long} must be greater than zero.");
        musicBandFieldsDescription.put("singlesCount",         "Field 'number of singles' {int} must be greater than zero.");
        musicBandFieldsDescription.put("genre",                "Field music 'genre' {Genre} can not be null and must have value from: \n" + MusicGenre.showValues());
        musicBandFieldsDescription.put("frontMan",             "Field 'front man' {Person}.");
    }


    @Override
    public String toString() {
        return "MusicBand{" + "\n" +
                "    id = ................. " + id + "\n" +
                "    name = ............... " + name + "\n" +
                "    creationDate = ....... " + creationDate + "\n" +
                "    numberOfParticipants = " + numberOfParticipants + "\n" +
                "    singlesCount = ....... " + singlesCount + "\n" +
                "    genre = .............. " + genre + "\n\n" +
                "    coordinates = " + coordinates + "\n\n" +
                "    frontMan = " + frontMan + "\n\n" +
                '}';
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        if (name == null || name.isEmpty() || name.matches("[\\s]*")) throw new IllegalArgumentException("Error: " + musicBandFieldsDescription.get("name"));
        this.name = name;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }


    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException(musicBandFieldsDescription.get("coordinates"));
        this.coordinates = coordinates;
    }


    public Date getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }


    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }


    public void setNumberOfParticipants(long numberOfParticipants) {
        if (numberOfParticipants <= 0) throw new IllegalArgumentException(musicBandFieldsDescription.get("numberOfParticipants"));
        this.numberOfParticipants = numberOfParticipants;
    }


    public int getSinglesCount() {
        return singlesCount;
    }


    public void setSinglesCount(int singlesCount) {
        if (singlesCount <= 0) throw new IllegalArgumentException(musicBandFieldsDescription.get("singlesCount"));
        this.singlesCount = singlesCount;
    }


    public MusicGenre getGenre() {
        return genre;
    }


    public void setGenre(MusicGenre genre) {
        if (genre == null) throw new IllegalArgumentException(musicBandFieldsDescription.get("genre"));
        this.genre = genre;
    }


    public Person getFrontMan() {
        return frontMan;
    }


    public void setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
    }


    @Override
    public int compareTo(MusicBand musicBand) {
        return this.singlesCount - musicBand.getSinglesCount();
    }


    public boolean isLessThan(MusicBand musicBand) {
        return compareTo(musicBand) < 0;
    }


    public boolean isMoreThan(MusicBand musicBand) {
        return compareTo(musicBand) > 0;
    }


    public boolean equalsTo(MusicBand musicBand) {
        return compareTo(musicBand) == 0;
    }



}
