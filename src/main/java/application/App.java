package application;

import java.util.Scanner;

public class App
{
    private static String inputMonth;
    private static String inputYear;

    public void runApp()
    {
        
        Database database = new Database(
            "jdbc:postgresql://localhost:port/weather_db", 
           "username", "password");

        DatabaseWrite.writeTextToDB("/home/w/Documents/coding/JAVA/db_worker/text/weather.txt", database);

        DatabaseGetData.printAverageColumnDouble(1950, 2001, "precipitations", database);
        DatabaseGetData.printAverageColumnDouble(1950, 2001, "snow", database);
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter month[1-12]: ");
        inputMonth = scanner.nextLine();

        System.out.println("Enter year[1950-2001]: ");
        inputYear = scanner.nextLine();

        scanner.close();

        DatabaseGetData.getMaxMinTemperature(inputMonth, inputYear, database);
    } 
}
