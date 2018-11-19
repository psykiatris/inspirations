/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
* This class is the language used between server and client.
* */
public class Protocol {

    // From Listen to Talk
    public static final String SUBMIT = "S";
    public static final String ACCEPTED = "A";
    public static final String REJECTED = "R";
    public static final String CHAT = "C";

    // From Talk to Listen
    public static final String NAME = "N";
    public static final String BROADCAST = "B";
    public static final String QUIT = "Q";

}
