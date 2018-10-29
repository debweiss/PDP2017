package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;


public class EmailTest {

    private HashMap<String, String> substitutions = new HashMap<>();
    private Email email = new Email(substitutions);
    private LocalDate newDate = LocalDate.now();


    @Before
    public void setUp() {

        substitutions.put("[[Date]]", "11/21/17");
        substitutions.put("[[First_Name]]", "Debra");
        substitutions.put("[[Last_Name]]", "Weissman");

    }

    @Test
    public void testGetCreatedDateAndSetCreatedDate() throws Exception {

        email.setCreationDate(newDate);
        Assert.assertEquals("should return new date",
            email.getCreationDate(), newDate

        );
    }

    @Test
    public void testGetSubstitutionsAndSetSubstitutions() throws Exception {

        email.setSubstitutions(substitutions);
        Assert.assertEquals("should return substitutions'",
            email.getSubstitutions().entrySet(), substitutions.entrySet()
        );
    }
}