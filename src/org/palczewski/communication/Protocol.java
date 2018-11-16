/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;
/*
* This class is the language used between server and client.
* */
public class Protocol {
    public static final String REQUEST = "R";   // Request name from client
    public static final String SUBMIT = "S";    // Submit name from
    // client to server
    public static final String CHAT = "C";  // Used to broadcast to all
    // clients.
    public static final String QUIT = "Q";  // When client quits server
    public static final String APPROVE = "A";   // When server accepts
    // client
    public static final String REJECT = "r";    ;// when server rejects
    // client
}
