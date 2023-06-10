//package org.example;
//
//import java.io.*;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
///*
//
//У попередньому завданні ми, писали методи, що читають всі SQL-файли, рідером, та закидували їх у statement.executeQuere(....), получався файлік *.db який ми виконували в базі данних;
//
//
//Завдання №1 - використай PreparedStatement
//
//Візьми проєкт з попереднього ДЗ. У цьому проєкті для всіх SQL запитів, що приймають параметри, використай PreparedStatement замість Statement.
//Завдання №2 - залий код на github
//
//Створи новий репозиторій на GitHub. Додай туди всі необхідні файли твого проєкту. Переконайсь, що в репозиторії немає зайвих файлів.
//
//Результат виконання ДЗ - GitHub репозиторій зі всіма файлами.
//
// */
//
//public class DatabasePopulateService {
//
//    public static void main(String[] args) {
//
//        try {
//        File file = new File("src\\sql\\populate_db.sql");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//
//        Statement statement = H2Database.getInstance().getH2Connection().createStatement();
//
//        String line;
//        String allLine = "";
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//            allLine +=line;
//
//        }
//        statement.execute(allLine);
//        reader.close();
//    } catch (SQLException ex) {
//        System.out.println( "oops sql" + ex.getMessage());
//    } catch (IOException ex) {
//        System.out.println( "oops something with File: " + ex.getMessage());
//    }
//
//    }
//}
//
//
//
//**************************************************************************************
package org.example;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DatabasePopulateService {

        public static void main(String[] args) {

            try {
                File file = new File("src\\sql\\populate_db.sql");
                BufferedReader reader = new BufferedReader(new FileReader(file));

                Statement statement = H2Database.getInstance().getH2Connection().createStatement();

                String line;
                String allLine = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    allLine +=line + "\n";

                }
                statement.execute(allLine);
                reader.close();
            } catch (SQLException ex) {
                System.out.println( "oops sql" + ex.getMessage());
            } catch (IOException ex) {
                System.out.println( "oops something wrong in File: " + ex.getMessage());
            }

        }
    }



