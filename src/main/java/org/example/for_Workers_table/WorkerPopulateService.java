package org.example.for_Workers_table;

import org.example.H2Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class WorkerPopulateService {


    private  static int counter = 1;
    private  static int counter2 = 0;
    private  static int counter3 = 0;

    private  static PreparedStatement insertStatement;

    public static void main(String[] args) {

        try {
            File file = new File("src\\sql\\preparestatement\\Workers_db_new.sql");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Connection connection = H2Database.getInstance().getH2Connection();

            String line;
            String allLine = "";
            while ((line = reader.readLine()) != null) {
                allLine += line;
            }
            insertStatement = connection.prepareStatement(allLine);
            WorkerPopulateService database = new WorkerPopulateService();
            for (int i = 0; i < 10; i++) {
                database.addWorker(database.createWorker());
            }
            reader.close();
        } catch (SQLException ex) {
            System.out.println("oops sql" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("oops something with File: " + ex.getMessage());
        }

    }


    public int addWorker(Worker worker) {
        try {
            this.insertStatement.setInt(1, worker.getId());
            this.insertStatement.setString(2, worker.getName());
            this.insertStatement.setDate(3, java.sql.Date.valueOf(worker.getBirthday().toString()));
            this.insertStatement.setString(4, worker.getLevelTechSkill());
            this.insertStatement.setInt(5, worker.getSalary());
            return this.insertStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Insert workers exception. Reason: " + ex.getMessage());
            return -1;
        }
    }


    public Worker createWorker() {
        int id = counter;
        String name = getNameMethod() + counter++;
        LocalDate birthday = LocalDate.now().minusYears(20-counter).minusMonths(12-counter).minusDays(31-counter);
        String levelTechSkill = getLevelTechSkillMethod();
        int salary = 0;

        switch (levelTechSkill){
            case "Trainee":
                salary = 500+counter;
                break;
            case "Junior":
                salary = 1000 + counter;
                break;
            case "Middle":
                salary = 1500 + counter;
                break;
            case "Senior":
                salary = 2000 + counter;
                break;
        }
        return new Worker(id, name, birthday, levelTechSkill, salary);
    }


    public String getNameMethod() {
        String current = "";
        String[] array = new String[]{"Mikey Mouse", "Jerry", "Porky Pig", "Aladdin", "Goofy", "Sinderela", "Winnie Pooh"};
        if (counter2 >= 7) counter2 = 0;
        current = array[counter2++];
        return current;
    }


    public String getLevelTechSkillMethod() {
        String current = "";
        String[] array = new String[]{"Trainee", "Junior", "Middle", "Senior"};
        if (counter3 >= 4) counter3 = 0;
        current = array[counter3++];
        return current;
    }

}
