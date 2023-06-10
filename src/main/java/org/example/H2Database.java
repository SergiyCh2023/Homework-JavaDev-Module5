package org.example;

import java.sql.*;

public class H2Database {

    private static final H2Database INSTANCE = new H2Database();
    private Connection H2Connection;


    H2Database()  {

        try {
            String h2ConnectionUrl = "jdbc:h2:./test";
            this.H2Connection = DriverManager.getConnection(h2ConnectionUrl);
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
        public static H2Database getInstance() {
            return INSTANCE;
        }

        public Connection getH2Connection() {
            return  H2Connection;
        }

        public int executeUpdate(String query) throws SQLException {
            try (Statement statement = INSTANCE.getH2Connection().createStatement()){
                return statement.executeUpdate(query);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }


        public void closeConnection() {
            try {
                H2Connection.close();
            }   catch (SQLException ex) {
                throw new RuntimeException();
            }
        }

    }



