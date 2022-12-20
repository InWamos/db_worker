package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseWrite {


    public static int writeTextToDB(String pathToTxt, Database db)
    {
        try {

            final BufferedReader reader = new BufferedReader(new FileReader(pathToTxt));
            final Connection connection = DriverManager.getConnection(db.urlToDB, db.username, db.password);
            Statement statement = connection.createStatement();
            int resultset = 0;
            String line;
            String[] parsedLine;
            String query;

            while((line = reader.readLine()) != null)
            {
                parsedLine = line.split("	");
                query = String.format("INSERT INTO weather (month, day, year, high_temp, low_temp, precipitations, snow) "
                + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                parsedLine[0], parsedLine[1], parsedLine[2], 
                Convertors.fagrengheitToCelsius(Integer.parseInt(parsedLine[3])), 
                Convertors.fagrengheitToCelsius(Integer.parseInt(parsedLine[4])), 
                parsedLine[5], parsedLine[6]);

                resultset += statement.executeUpdate(query);
            }
            
            connection.close();
            reader.close();

            System.out.println("QUERIES AFFECTED: " + resultset);
            return resultset;

        } catch(IOException e) {
            System.out.println("Error while reading a file!");
            e.printStackTrace();
            return 0;
        } catch(SQLException e) {

            System.out.println("Error in connecting to the DB!");
            e.printStackTrace();
            return 0;
        } 
    }


}
