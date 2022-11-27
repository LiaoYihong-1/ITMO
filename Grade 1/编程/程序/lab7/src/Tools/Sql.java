package Tools;

import Collection.EyeColor;
import Collection.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Sql {
    public static void main(String args[]) throws Exception{
        Class.forName("org.postgresql.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs","postgres","123456")){
                System.out.print(connection);
            }
        }
    }
