package edu.neu.ccs.cs5010;


import java.time.LocalDate;
import java.util.HashMap;


/**
 * The Email class contains a map that has the areas in the email template
 * that require substitution as the key, and the value to substitute as the
 * value.
 */
class Email {

    private LocalDate creationDate; // Date the email was created
    private HashMap<String, String> substitutions = new HashMap<>(); // placeholder text and repl text


    /**
     * Constructor -- sets the substitutions map
     *
     * @param substitutions HashMap containing template text areas and values to substitute
     */
    Email(HashMap<String, String> substitutions) {

        this.substitutions = substitutions;

    }

    /* *********************** Getters and Setters*************************/

    LocalDate getCreationDate() {

        return creationDate;
    }

    void setCreationDate(LocalDate creationDate) {

        this.creationDate = creationDate;
    }

    HashMap<String, String> getSubstitutions() {

        return this.substitutions;
    }

    void setSubstitutions(HashMap<String, String> substitutions) {

        this.substitutions = substitutions;
    }
}
