package com.csci360.electionapp.model;

import java.util.ArrayList;

public class Ballot {
    private Voter voter;
    private ArrayList<Vote> votes;
    private boolean submitted = false;

    public Ballot(Voter voter){
        this.voter = voter;
        votes.clear();

    }

    public ArrayList<Vote> getBallots(){
        return votes;
    }

    public void printBallot(){
        for(Vote v: votes){
            System.out.println(v);
        }
    }

    public ArrayList<Vote> getVotes(){
        return votes;
    }

    public void addVote(){

    }



}
