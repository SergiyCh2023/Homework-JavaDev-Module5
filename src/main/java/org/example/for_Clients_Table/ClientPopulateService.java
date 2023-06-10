package org.example.for_Clients_Table;

import org.example.H2Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientPopulateService {


    private  static int counter = 1;
    private  static int counter2 = 0;

    private  static PreparedStatement insertStatement;

    public static void main(String[] args) {

        try {
            File file = new File("src\\sql\\preparestatement\\Clients_db_new.sql");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Connection connection = H2Database.getInstance().getH2Connection();

            String line;
            String allLine = "";
            while ((line = reader.readLine()) != null) {
                allLine += line;
            }
            insertStatement = connection.prepareStatement(allLine);
            ClientPopulateService database = new ClientPopulateService();
            for (int i = 0; i < 5; i++) {
                database.addClient(database.createClient());
            }
            reader.close();
        } catch (SQLException ex) {
            System.out.println("oops sql" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("oops, something wrong with File: " + ex.getMessage());
        }

    }


    public int addClient(Client client) {
        try {
            this.insertStatement.setInt(1, client.getId());
            this.insertStatement.setString(2, client.getName());
            return this.insertStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Insert client exception. Reason: " + ex.getMessage());
            return -1;
        }
    }


    public Client createClient() {
        int id = counter;
        String name = getNameMethod() + counter++;
        return new Client(id, name);
    }



    public String getNameMethod() {
        String current = "";
        String[] array = new String[]{"Minnie", "Tom", "Donald Duck", "Bugs Bunny", "Piter Pen"};
        if (counter2 >= 5) counter2 = 0;
        current = array[counter2++];
        return current;
    }


}
