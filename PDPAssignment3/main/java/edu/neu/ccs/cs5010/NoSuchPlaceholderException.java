package edu.neu.ccs.cs5010;

/**
 * Exception occurs when there is a set of placeholder text
 * (e.g., [[placeholder text]]) that is not recognized by
 * the program.
 */
class NoSuchPlaceholderException extends Exception {

    NoSuchPlaceholderException(String message) {

        super(message);
    }
}
