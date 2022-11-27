package Tools;

import Collection.EyeColor;
import Collection.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Sql {
    public static void main(String args[]) throws Exception{
        /*Class.forName("org.postgresql.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs","postgres","123456")){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO studs (id) values (?)")){
                ps.setObject(1,23);
                int n = ps.executeUpdate();
                System.out.print(n);
            }
        }*/System.out.print(2);
    }
}
