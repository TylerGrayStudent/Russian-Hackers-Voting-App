package com.csci360.electionapp.foundation;

import com.csci360.electionapp.model.*;
import com.csci360.electionapp.tech.security.Decryption;
import com.csci360.electionapp.tech.security.Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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

    public HashMap<Office, String> getResults(Election election) throws Exception {
        HashMap<Office, String> results = new HashMap<>();
        for(Office o: election.getOffices()){
            Connection dbConnection = getConnection();
            String sql = "SELECT * FROM " + o.nameOfOffice + " ORDER BY vote_count DESC";
            PreparedStatement res = dbConnection.prepareStatement(sql);
            //res.setString(1,o.getNameOfOffice());
            ResultSet rs = res.executeQuery();
            rs.next();
            results.put(o,rs.getString(1) + " - " + rs.getString(2));

        }
        return results;

    }

    public boolean checkVotedStatus(String userName) throws Exception{
        Connection dbConnection = getConnection();
        String getUserIDSQL = "SELECT * from users where username = ?";
        PreparedStatement getUserIDStatement = dbConnection.prepareStatement(getUserIDSQL);
        getUserIDStatement.setString(1,userName);
        ResultSet rs = getUserIDStatement.executeQuery();
        rs.next();

        String voted = rs.getString(4);
        if(voted.equals("0")){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkRegStatus(String userName) throws Exception{
        Connection dbConnection = getConnection();
        String getUserIDSQL = "SELECT userID from users where username = ?";
        PreparedStatement getUserIDStatement = dbConnection.prepareStatement(getUserIDSQL);
        getUserIDStatement.setString(1,userName);
        ResultSet rs = getUserIDStatement.executeQuery();
        if(!rs.next()){
            return false;
        }
        else{
            return true;
        }

    }


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

    public void updateUserByID(ArrayList<String> newData) throws Exception {
        String sql = "UPDATE `election_system`.`voterInfo` SET " +
                "`firstName` = ? ," +
                "`lastName` = ? ," +
                "`address` = ? ," +
                "`zipCode` = ? ," +
                "`ssn` = ? ," +
                "`dlNumber` = ? " +
                "WHERE `userID` = ?;";

        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        for(int i = 1; i < newData.size();i++) {
            statement.setString(i, newData.get(i));
        }
        statement.setString(7,newData.get(0));
        System.out.println(statement);
        statement.executeUpdate();

    }

    public  ArrayList<String> getUserData(String userName) throws Exception {
        ArrayList<String> info = new ArrayList<>();
        Connection connection = getConnection();

        String sql = "SELECT userID from users where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,userName);

        ResultSet userIDRS = statement.executeQuery();
        userIDRS.next();
        String userID = userIDRS.getString(1);

        String getNameSQL = "SELECT * from voterInfo where userID = ?";
        PreparedStatement getNameStatement = connection.prepareStatement(getNameSQL);
        getNameStatement.setString(1,userID);

        ResultSet rs = getNameStatement.executeQuery();
        rs.next();

        Decryption decryption = new Decryption();

        for(int i = 1; i <= 7; i++ ){
            if(i == 6 || i == 7){
                info.add(decryption.decrypt(rs.getString(i)));
            }
            else {


                info.add(rs.getString(i));
            }
           // System.out.println(i);
           // System.out.println(rs.getString(i));

        }

        return info;









    }
    public void resetVoterStatus() throws Exception {

        String sql = "UPDATE `election_system`.`users` SET `voted` = 0;";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

    }

    public void updateVotedStatus(Voter v) throws Exception {
        String sql = "UPDATE `election_system`.`users` SET `voted` = 1 WHERE (`userID` = ?);";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,v.getUserID());
        statement.executeUpdate();
    }


    public void castVote(Ballot b) throws Exception {
        PreparedStatement statement = null;
        Connection connection = getConnection();



        for(Vote v:b.getVotes()){
            String sql = "UPDATE " + v.getOffice() + " SET  `vote_count` = `vote_count` + 1 WHERE candidate = \"" + v.getCandidate() + "\"";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
    }

    public void publishElection(Election election) throws Exception {
        Connection connection = getConnection();
        PreparedStatement createDB = null;
        PreparedStatement dropTable = null;
        PreparedStatement statement = null;

        //Drop Table if Exists
        String dropTablesql = String.format("DROP TABLE IF EXISTS %s",election.getNameOfElection());
        dropTable = connection.prepareStatement(dropTablesql);
        dropTable.execute();

        //create table
        String sql = "CREATE TABLE `election_system`." + election.getNameOfElection() +
                "  (`officeID` INT NOT NULL AUTO_INCREMENT,";

        for(Office o:election.getOffices()){
            sql = sql + "" + o.getNameOfOffice() + " VARCHAR(45) NOT NULL,";
        }
        sql = sql + "  PRIMARY KEY (`officeID`));";

        createDB = connection.prepareStatement(sql);
        createDB.execute();

        //
        for(Office o: election.getOffices()) {
            dropTablesql = String.format("DROP TABLE IF EXISTS %s", o.getNameOfOffice());
            dropTable = connection.prepareStatement(dropTablesql);
            dropTable.execute();
            sql = "CREATE TABLE " + o.getNameOfOffice() + " ( `candidate` VARCHAR(45) NOT NULL, `vote_count` INT ,PRIMARY KEY (`candidate`));";
        System.out.println(sql);
            createDB = connection.prepareStatement(sql);
            createDB.execute();
        }

        for(Office o: election.getOffices()){

            for(String candidate: o.getCandidates()){
                sql = "INSERT INTO " + o.getNameOfOffice() + " VALUES (\"" + candidate + "\" , 0);";
                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.execute();
            }

        }

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


}
