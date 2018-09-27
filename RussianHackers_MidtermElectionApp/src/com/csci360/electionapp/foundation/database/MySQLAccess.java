package com.csci360.electionapp.foundation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String dbName = "Test";
    private String dbUserName = "Tyler";
    private String dbPassword = "g7yz8i8r";
    private String dbIP = "electionsystem.c9taptqlaltr.us-west-2.rds.amazonaws.com";
    private String connectionString = "jdbc:mysql://" + dbIP + ":3306/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;

    public void readDataBase() throws Exception {
        try {

            // Setup the connection with the DB
            //String connectionString = "jdbc:mysql://" + dbIP + ":3306/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;
            connect = DriverManager
                    .getConnection(connectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from Test.users");
            writeResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void connectToDB() throws Exception{
        connect = DriverManager.getConnection(connectionString);
        statement = connect.createStatement();


    }

    private void handleLogInButton(){

    }



    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("userID");
            String website = resultSet.getString("userFirstName");
            String summary = resultSet.getString("userLastName");
            String password = resultSet.getString("userPassword");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + password);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
    }
}
