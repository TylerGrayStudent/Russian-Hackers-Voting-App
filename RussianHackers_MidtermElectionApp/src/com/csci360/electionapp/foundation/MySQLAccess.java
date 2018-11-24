package com.csci360.electionapp.foundation;

import com.csci360.electionapp.model.NewVoter;
import com.csci360.electionapp.tech.security.Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
   /* public void readDataBase() throws Exception {
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

    }*/

    public String[] getUserInfo(String userName) throws Exception {
        String[]userInfo = new String[2];
        Connection dbConnection = getConnection();
        String getUserIDSQL = "SELECT userID from users where username = ?";
        PreparedStatement getUserIDStatement = dbConnection.prepareStatement(getUserIDSQL);
        getUserIDStatement.setString(1,userName);

        ResultSet userIDRS = getUserIDStatement.executeQuery();
        userIDRS.next();
        String userID = userIDRS.getString(1);

        userInfo[0] = userID;

        String getNameSQL = "SELECT firstName from voterInfo where userID = ?";
        PreparedStatement getNameStatement = dbConnection.prepareStatement(getNameSQL);
        getNameStatement.setString(1,userID);

        ResultSet firstNameRDS = getNameStatement.executeQuery();
        firstNameRDS.next();
        String firstName = firstNameRDS.getString(1);
        userInfo[1] = firstName;

        return userInfo;
    }

    public void publishElection(){

    }


    public boolean verifyLogIn(String userName, String passwordHash) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement logIn = null;
            String sql = "select *  from users where username = ?";
            logIn = connection.prepareStatement(sql);
            logIn.setString(1,userName);
            resultSet = logIn.executeQuery();
            if (resultSet.next()) {

                System.out.println(resultSet.getString("password"));
                boolean passwordsMatch = Security.validatePassword(passwordHash, resultSet.getString("password"));
                if (passwordsMatch) {
                    //System.out.println("PASSWORDS MATCH");
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("COULDNT CONNECT TO DB");
            return false;

        } finally {
            close();
        }

    }

    public boolean verifyAdminLogIn(String userName, String passwordHash) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement logIn = null;
            String sql = "select *  from admin where username = ?";
            logIn = connection.prepareStatement(sql);
            logIn.setString(1,userName);
            resultSet = logIn.executeQuery();
            if (resultSet.next()) {

                System.out.println(resultSet.getString("password"));
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
            //e.printStackTrace();
            System.out.println("COULDNT CONNECT TO DB");
            return false;

        } finally {
            close();
        }

    }

    private Connection getConnection() throws Exception{
        connect = DriverManager.getConnection(connectionString);
        return connect;

    }


    public void addNewRegisteredVoter(NewVoter voter) throws Exception {
        String passwordHashed = Security.generateStorngPasswordHash(voter.getPassword());
        PreparedStatement createUserLogIn = null;
        PreparedStatement getUserID = null;
        PreparedStatement createVoterInfo = null;
        Connection dbConnection = getConnection();

        String createUserNameandPassword =
                "INSERT INTO  users (username, password) VALUES (? , ?)";
        createUserLogIn = dbConnection.prepareStatement(createUserNameandPassword);
        createUserLogIn.setString(1,voter.getUserName());
        createUserLogIn.setString(2,passwordHashed);
        createUserLogIn.execute();

        String getUserIDStatement = "SELECT userID from users where username = ?";
        getUserID = dbConnection.prepareStatement(getUserIDStatement);
        getUserID.setString(1,voter.getUserName());
        ResultSet userIDRS = getUserID.executeQuery();
        userIDRS.next();
        String userID = userIDRS.getString(1);
        System.out.println(userID);

        String createVoterInfoStatement = "INSERT INTO  " + dbName + ".voterInfo " +
                "(USERID, firstName, lastName, address, zipCode, ssn, dlnumber) VALUES (?,?,?,?,?,?,?)";
        createVoterInfo = dbConnection.prepareStatement(createVoterInfoStatement);
        createVoterInfo.setString(1, userID);
        createVoterInfo.setString(2, voter.getFirstName());
        createVoterInfo.setString(3, voter.getLastName());
        createVoterInfo.setString(4, voter.getAddress());
        createVoterInfo.setString(5, voter.getZipCode());
        createVoterInfo.setString(6, voter.getSsn());
        createVoterInfo.setString(7, voter.getDlNumber());
        createVoterInfo.execute();


        dbConnection.close();

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

    }
}
