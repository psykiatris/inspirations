/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
* This is where the server is defined. It may be GUI in the future, but
* that's a different class file.
* */
public class Listen {
    private static final long serialVersionUID = 1L;
    private final int PORT_NUMBER = 40000;
    private ServerSocket server;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    //Constructor
    Listen() {

    }

    public void log() {
        /*
        * This will log messages regardomg actions, such as connections
        * to the server, chat, etc. Will be saved to a local log file
        * */
    }
}
