package com.csci360.electionapp.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitTests {

    public Voter v;
    public Election e;


    @BeforeEach
    public void setUp() throws Exception {
        v = new Voter("Tyler", "1");
        e = new Election("Thing");
    }

    @Test
    public void disallowVoter(){
        v.unallowToVote();
        assertEquals(false, v.checkedIfCanVote());
    }

    @Test
    public void allowVoter(){
        v.allowToVote();
        assertEquals(true, v.checkedIfCanVote());
    }

    @Test
    public void testAddOffice(){
        Office o = new Office("President");
        e.addOffice(o);
        assertEquals(true,e.contains(o));
    }
}
