package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;

public class DatabaseInitService {

   private static Connection connection = H2Database.getInstance().getH2Connection();


    public static void main(String[] args) throws SQLException, IOException {
       try {

            File file = new File("src\\sql\\init_db.sql");
            BufferedReader reader = new BufferedReader(new FileReader(file));



            String line;
            String allLine = "";
            while ((line = reader.readLine()) != null) {
                allLine +=line + "\n";
            }
            Connection connection = H2Database.getInstance().getH2Connection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(allLine);
            reader.close();
        } catch (SQLException ex) {
            System.out.println( "oops sql" + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println( "oops something with File: " + ex.getMessage());
        }
    }

}