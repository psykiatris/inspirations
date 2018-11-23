/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.protocol;
/*
* This class manages the transactions between server and client, passing
 * messages back and forth.
 * */
public class Protocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold in here",
    "I didn't know you could yodel!",
    "Bless you",
    "Is there an owl in here?",
    "Is there an echo in here?"};

    public String processInput(String theInput) {
        String theOutput = null;

        if(state == WAITING) {
            theOutput = "Knock knock";
            state = SENTKNOCKKNOCK;
        } else if(state == SENTKNOCKKNOCK) {
            if(theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else if(theInput.equalsIgnoreCase(currentJoke + "who?")) {
                theOutput = answers[currentJoke];
            }else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                        "Try again. Knock Knock";
                state = SENTKNOCKKNOCK;
            }
        } else if(state == ANOTHER) {
            if(theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if(currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;

            } else {
                theOutput = "Bye";
                state = WAITING;
            }
        }
        return theOutput;
    }


}
