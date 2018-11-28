package com.csci360.electionapp.model;

public class Voter {
    private String name;
    private String userID;
    private boolean allowedToVote;

    public Voter(String name, String userID){
        this.name = name;
        this.userID = userID;
        allowedToVote = true;
    }

    public String getUserID(){ return userID; }
    public boolean checkedIfCanVote(){
        return allowedToVote;
    }

    public void allowToVote(){
        allowedToVote = true;
    }
    public void unallowToVote(){
        allowedToVote = false;
    }

    public boolean validateVoter(){
        return true;
    }

    public String getName(){ return name; }





}
