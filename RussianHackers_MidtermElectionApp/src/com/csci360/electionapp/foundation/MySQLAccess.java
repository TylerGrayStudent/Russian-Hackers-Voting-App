package com.csci360.electionapp.foundation;

import com.csci360.electionapp.tech.security.Security;

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
    private String dbName = "election_system";
    private String dbUserName = "Tyler";
    private String dbPassword = "g7yz8i8r";
    private String dbIP = "electionsystem.c9taptqlaltr.us-west-2.rds.amazonaws.com";
    private String connectionString = "jdbc:mysql://" + dbIP + ":3306/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;


    public MySQLAccess(){

    }
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


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    public boolean verifyLogIn(String userName, String passwordHash) throws Exception {
        try {
            Connection connection = getConnection();
            statement = connection.createStatement();
            String sql = "select *  from users where username = '" + userName + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                //System.out.println(resultSet.getString("password"));
                boolean passwordsMatch = Security.validatePassword(passwordHash, resultSet.getString("password"));
                if (passwordsMatch) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;

        } finally {
            close();
        }

    }

    private Connection getConnection() throws Exception{
        connect = DriverManager.getConnection(connectionString);
        return connect;

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
