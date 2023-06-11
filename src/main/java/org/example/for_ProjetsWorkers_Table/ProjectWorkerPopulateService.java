package org.example.for_ProjetsWorkers_Table;

import org.example.H2Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class ProjectWorkerPopulateService {

    private  static int counter = 0;


    private  static PreparedStatement insertStatement;

    public static void main(String[] args) {

        try {
            File file = new File("src\\sql\\preparestatement\\ProjectWorker_db_new.sql");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Connection connection = H2Database.getInstance().getH2Connection();

            String line;
            String allLine = "";
            while ((line = reader.readLine()) != null) {
                allLine += line;
            }
            insertStatement = connection.prepareStatement(allLine);
            ProjectWorkerPopulateService database = new ProjectWorkerPopulateService();
            for (int i = 0; i < 20; i++) {
                database.addProjectWorker(database.createProjectWorker());
            }
            reader.close();
        } catch (SQLException ex) {
            System.out.println("oops sql" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("oops, something wrong with File: " + ex.getMessage());
        }

    }


    public int addProjectWorker(ProjectWorker projectWorker) {
        try {
            this.insertStatement.setInt(1, projectWorker.getProjectId());
            this.insertStatement.setInt(2, projectWorker.getWorkerId());
            return this.insertStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Insert projectWorker exception. Reason: " + ex.getMessage());
            return -1;
        }
    }


    public ProjectWorker createProjectWorker() {
        int projectId = new Random().nextInt(10) + 1;
        int workerId = new Random().nextInt(10) + 1;
        return new ProjectWorker(projectId, workerId);
    }

    public int getProjectId() {
        counter++;
        int result = 1;
        if (counter<=10) return counter;
        else return new Random().nextInt(10) + 1;
    }


}
