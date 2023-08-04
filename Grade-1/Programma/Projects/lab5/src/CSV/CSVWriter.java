package CSV;

import Collection.Person;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * write in format csv
 */
public class CSVWriter {

    /**
     *
     * @param linkedHashSet
     * @param path
     * @throws IOException
     */
    public void WriterToFile(LinkedHashSet<Person> linkedHashSet,String path) throws IOException{
        File file=new File(path);
        String firstline="";
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String addfirstline="id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday\n";
        BufferedOutputStream BOS=new BufferedOutputStream(new FileOutputStream(file,true));
        firstline=bufferedReader.readLine();
        if(firstline==null||!(firstline.equals("id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday"))){
            BOS.write(addfirstline.getBytes());
        }
        Iterator<Person> iterator=linkedHashSet.iterator();
        while (iterator.hasNext()){
            BOS.write(iterator.next().toString().getBytes());
        }
        bufferedReader.close();
        BOS.close();
    }
}