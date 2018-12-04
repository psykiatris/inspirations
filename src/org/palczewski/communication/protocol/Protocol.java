/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.protocol;

import org.palczewski.communication.listen.TheServer;

/*
* This class provides the "translation" the server and client must
* communicate with each other.
*
* It will process messages from one to th euser, via the processin
* method (which may change)
 * */
public class Protocol {
    private static final int WAITING = 0;
    private static final int SENTREQ = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;


    public String processInput(String theInput) {
        String theOutput = null;

        switch(state) {
            case WAITING:
                theOutput = "Welcome to the Pat Chat. Enter your name.";

                state = SENTREQ;
                break;
            case SENTREQ:
                String temp = theInput;
                TheServer.log(temp + " was submitted.");
                break;
        }
        return theOutput;
    }


}
