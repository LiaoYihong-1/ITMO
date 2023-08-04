package CSV;

import Collection.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.util.LinkedHashSet;

/**
 * read a csv file
 */
public class CSVReader {
    /**
     * read a csv file and write it to a linkedhashset
     * @param peoplelinkedhashset which will be written
     * @param  path File
     * @throws IOException by FileReader and readline()
     */
    public void ReadFile(LinkedHashSet<Person> peoplelinkedhashset, String path) throws IOException{
        FileReader fileReader=new FileReader(path);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        bufferedReader.readLine();
        String s;
        while((s = bufferedReader.readLine())!=null) {
            String[] information = s.split(",");
            Coordinates coordinates = new Coordinates(Integer.valueOf(information[6]), Integer.valueOf(information[7]));
            Location location = new Location(coordinates, information[5], Integer.valueOf(information[8]));
            Person p = new Person(location, HairColor.valueOf(information[2].toUpperCase()), EyeColor.valueOf(information[3].toUpperCase()), information[1], Integer.valueOf(information[4]));
            Person.balaceicode();
            p.changeId(Integer.valueOf(information[0]));
            p.setCreationDate(LocalDate.parse(information[9]));
            p.setBirthday(ZonedDateTime.parse(information[10]));
            peoplelinkedhashset.add(p);
        }
    }
}
