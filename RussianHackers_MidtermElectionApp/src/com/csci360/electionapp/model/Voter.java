package com.csci360.electionapp.model;

public class Voter {
    private String name;
    private String voterID;
    private boolean allowedToVote;

    public Voter(String name, String voterID){
        this.name = name;
        this.voterID = voterID;
        allowedToVote = true;
    }

    public boolean checkedIfCanVote(){
        return allowedToVote;
    }

    public void allowToVote(){
        allowedToVote = true;
    }
    public void unallowToVote(){
        allowedToVote = false;
    }



}
