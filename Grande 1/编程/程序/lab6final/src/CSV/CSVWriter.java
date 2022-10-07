package CSV;

import Collection.Person;

import java.io.*;
import java.util.LinkedHashSet;

/**
 * write in format csv
 */
public class CSVWriter {

    /**
     * @param linkedHashSet write file to
     * @param path          File
     * @throws IOException by File and readline()
     */
    public void writetofile(LinkedHashSet<Person> linkedHashSet, String path) throws IOException {
        File file = new File(path);
        String firstline;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String addfirstline = "id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday\n";
        BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(file));
        firstline = bufferedReader.readLine();
        if (firstline == null || !(firstline.equals("id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday"))) {
            BOS.write(addfirstline.getBytes());
            BOS.flush();
        }
        if(linkedHashSet!=null) {
            for (Person person : linkedHashSet) {
                BOS.write(person.toString().getBytes());
            }
        }
        bufferedReader.close();
        BOS.close();
        //return linkedHashSet;
    }
}