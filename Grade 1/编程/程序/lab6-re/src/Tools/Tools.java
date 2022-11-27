package Tools;

import java.util.*;
import Collection.*;
import Command.*;

public class Tools {
    public static String Input(){
        return new Scanner(System.in).nextLine();
    }

    public void PrintPersonSet(LinkedHashSet<Person> linkedHashSet){
        Iterator<Person> iterator=linkedHashSet.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next().toString());
        }
    }
}
