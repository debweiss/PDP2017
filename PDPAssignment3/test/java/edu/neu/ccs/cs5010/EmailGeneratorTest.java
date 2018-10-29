package edu.neu.ccs.cs5010;

import org.junit.Test;

public class EmailGeneratorTest {
    

    @Test
    public void testMain() throws Exception {
    
        String[] goodUserInput = {"--email-template", "/Users/debweiss/Desktop/email-template.txt",
            "--output-dir", "emailOutput", "--csv-file",  "Flight363FromSeattleToBoston.csv",
            "--event", "arrival"};
    
        String[] goodUserInputVancouver = {"--email-template", "/Users/debweiss/Desktop/email-template.txt",
            "--output-dir", "emailOutput", "--csv-file",  "/Users/debweiss/Desktop/Flight3FromVancouverToSeattle.csv",
            "--event", "arrival"};
        
        
        EmailGenerator.main(goodUserInput);
        
        EmailGenerator.main(goodUserInputVancouver);

    }

}