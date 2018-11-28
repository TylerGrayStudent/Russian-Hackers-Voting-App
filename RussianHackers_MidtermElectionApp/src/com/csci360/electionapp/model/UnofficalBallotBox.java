package com.csci360.electionapp.model;

import com.csci360.electionapp.foundation.MySQLAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class UnofficalBallotBox {
    private ArrayList<Ballot> ballotBox;
    private ArrayList<String> voteCounts;
    private ArrayList<Office> offices;
    private ArrayList<ArrayList<String>> results;


    public UnofficalBallotBox(ArrayList<Office> offices){
        ballotBox = new ArrayList<Ballot>();
        voteCounts = new ArrayList<String>();
        this.offices = offices;
        results = new ArrayList<ArrayList<String>>();
    }

    public void addBallot(Ballot b){
        ballotBox.add(b);
    }

    public void getResults(){
        for(int i = 0; i < offices.size();i++){
            results.add(new ArrayList<String>());
            System.out.println(offices.get(i).nameOfOffice );
            for(Ballot b: ballotBox){
                results.get(i).add(b.getVotes().get(i).getCandidate());
                System.out.println(b.getVotes().get(i));
            }


        }


    }

    public void recount(Election election) throws Exception {
        MySQLAccess db = new MySQLAccess();
        db.publishElection(election);
        for(Ballot b: ballotBox){
            db.castVote(b);
        }


    }



    public static void main(String args[]){

    }
}
