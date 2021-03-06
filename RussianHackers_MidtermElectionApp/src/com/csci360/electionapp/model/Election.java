package com.csci360.electionapp.model;

import java.util.ArrayList;

public class Election {
    private String nameOfElection; public String getNameOfElection(){return nameOfElection;};
    private ArrayList<Office> offices; public ArrayList<Office> getOffices(){return offices;}
    private boolean active;


    public Election(String name){
        nameOfElection = name;
        offices = new ArrayList<Office>();
        active = false;
    }

    public boolean isActive(){
        return active;
    }

    public void addOffice(Office o){
        if(offices.contains(o)){
            return;
        }
        offices.add(o);
    }

    public void removeOffice(Office o){
        offices.remove(o);
    }

    public void removeOfficeByName(String name){
        int indexOfOffice = getOfficeIndexByName(name);
        if(indexOfOffice!= -1){
            offices.remove(indexOfOffice);
        }

    }

    public int getOfficeIndexByName(String name){
        for(int i = 0; i < offices.size(); i++){
            if(offices.get(i).getNameOfOffice().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public Office getOfficeByName(String name){
        for(Office o: offices){
            if(o.getNameOfOffice().equals(name)){
                return o;
            }
        }

            return null;

    }
    public boolean contains(Office o){
        for(Office office: offices){
            if(o.equals(office)){
                return true;
            }
        }
        return false;
    }

    public void publishElection(){
        active = true;
    }

    public boolean containsNameOf(String officeName){
        for(Office o: offices){
            if(officeName.equals(o.getNameOfOffice())){
                return true;
            }
        }
        return false;
    }

    public void endElectin(){
        active = false;
    }

}
