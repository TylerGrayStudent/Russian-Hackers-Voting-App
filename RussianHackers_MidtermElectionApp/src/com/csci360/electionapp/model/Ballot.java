package com.csci360.electionapp.model;

import com.csci360.electionapp.foundation.MySQLAccess;

import java.util.ArrayList;

public class Ballot {
    private Voter voter;
    private ArrayList<Vote> votes;
    private boolean submitted = false;

    public Ballot(Voter voter){
        this.voter = voter;
        votes = new ArrayList<Vote>();


    }

   // public ArrayList<Vote> getBallots(){
   //     return votes;
    //}

    public void printBallot(){
        for(Vote v: votes){
            System.out.println(v);
        }
    }

    public ArrayList<Vote> getVotes(){
        return votes;
    }

    public void addVote(Vote v){
        votes.add(v);
    }

    public Voter getVoter(){ return voter; }

    public void castBallotToDB() throws Exception {
        MySQLAccess db = new MySQLAccess();
        db.castVote(this);

    }



}
