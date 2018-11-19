package com.csci360.electionapp.model;

public class VotingMachine {
    public PollingPlace pollingPlace;
    public Ballot ballot;

    public boolean voterSignIn(String userID, String password){
        return true;
    }

    public boolean officialSignIn(String userID, String password) {
        return true;
    }

}
