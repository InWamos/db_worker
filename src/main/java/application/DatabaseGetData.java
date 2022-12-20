package application;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseGetData {
    
    public static void printAverageColumnDouble(int fromYear, int toYear, String columnName, Database db) {
        try {

            final Connection connection = DriverManager.getConnection(db.urlToDB, db.username, db.password);
            Statement statement = connection.createStatement();

            for(int i = 0; i < (toYear - fromYear) + 1; i++)
            {  
                ResultSet averageData = statement.executeQuery(
                    "SELECT AVG(" + columnName + ") " 
                    + "FROM weather " 
                    + "WHERE year = " + (fromYear + i));

                while (averageData.next()) {

                    System.out.println("Average " + columnName + " in " 
                                    + (fromYear + i) + ": " 
                                    + Double.parseDouble(averageData.getString(1)));
                }
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("Error in connecting to the DB!");
            e.printStackTrace();
        }
    }

    public static void getMaxMinTemperature(String month, String year, Database db) {

        try {

            final Connection connection = DriverManager.getConnection(db.urlToDB, db.username, db.password);
            Statement statement = connection.createStatement();
            
            ResultSet hightestTemp = statement.executeQuery("SELECT MAX(high_temp)"
                                    + "FROM weather WHERE year = "
                                    +  year + " AND month = " + month);

            while (hightestTemp.next()) {
                System.out.println("Hightest temperature during " + month + '/' + year
                                   + " is " + hightestTemp.getString(1));
            }

            ResultSet lowestTemp = statement.executeQuery("SELECT MIN(low_temp)"
            + "FROM weather WHERE year = "
            +  year + " AND month = " + month);

            while (lowestTemp.next()) {

                System.out.println("\nLowest temperature during " + month + '/' + year
                                   + " is " + lowestTemp.getString(1));
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("Error in connecting to the DB!");
            e.printStackTrace();
        }

    }
}
