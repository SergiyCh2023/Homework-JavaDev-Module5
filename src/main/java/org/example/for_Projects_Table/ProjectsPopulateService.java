package org.example.for_Projects_Table;

import org.example.H2Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

public class ProjectsPopulateService {


    private  static int counter = 1;

    private  static PreparedStatement insertStatement;

    public static void main(String[] args) {

        try {
            File file = new File("src\\sql\\preparestatement\\Projects_db_new.sql");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Connection connection = H2Database.getInstance().getH2Connection();

            String line;
            String allLine = "";
            while ((line = reader.readLine()) != null) {
                allLine += line;
            }
            insertStatement = connection.prepareStatement(allLine);
            ProjectsPopulateService database = new ProjectsPopulateService();
            for (int i = 0; i < 10; i++) {
                database.addProject(database.createProject());
            }
            reader.close();
        } catch (SQLException ex) {
            System.out.println("oops sql" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("oops something with File: " + ex.getMessage());
        }

    }


    public int addProject(Project project) {
        try {
            this.insertStatement.setInt(1, project.getId());
            this.insertStatement.setInt(2, project.getClientId());
            this.insertStatement.setDate(3, java.sql.Date.valueOf(project.getStartDate().toString()));
            this.insertStatement.setDate(4, java.sql.Date.valueOf(project.getFinishDate().toString()));

            return this.insertStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Insert projects exception. Reason: " + ex.getMessage());
            return -1;
        }
    }


    public Project createProject() {
        int id = counter++;
        int clientId = new Random().nextInt(5) + 1;
        LocalDate startDate = LocalDate.now().minusYears(5-counter).minusMonths(12-counter).minusDays(31-counter);
        LocalDate finishDate = LocalDate.now().minusYears(3-counter).minusMonths(11-counter).minusDays(30-counter);
        return new Project(id, clientId, startDate, finishDate);
    }

}
