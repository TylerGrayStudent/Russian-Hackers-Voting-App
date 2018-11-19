package com.csci360.electionapp.model;

public class PollingPlace {
    public String county;
    public String district;
    public String state;
    public ElectionOfficial primaryOfficial;

    public PollingPlace(String county, String district, String state, ElectionOfficial primaryOfficial){
        this.county = county;
        this.district = district;
        this.state = state;
        this.primaryOfficial = primaryOfficial;
    }

    public String getCounty(){return county;}
    public String getDistrict(){return district;}
    public String getState(){return state;}
    public ElectionOfficial getPrimaryOfficial(){return primaryOfficial;}


}
