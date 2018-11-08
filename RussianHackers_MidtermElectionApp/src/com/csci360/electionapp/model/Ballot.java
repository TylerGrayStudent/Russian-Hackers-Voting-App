package com.csci360.electionapp.model;

import java.util.ArrayList;

public class Ballot {
<<<<<<< HEAD
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
=======

    private String pres = "";
    private String vp = "";

    private ArrayList<String> votes;

    public ArrayList<String> getVotes(){
        return votes;
    }

    public void addVote(){

    }





>>>>>>> 4a74c761d60bd020ff3e979b670dcb008c48869c
}
