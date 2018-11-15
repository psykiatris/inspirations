/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
* This is the client. It will use Mediator to communicate with Listen
* (server) and other talkers (clients)
*
* Will log in with the server, etc.
* */
public class Talk {
    private static final long serialVersionUID = 1L;
    private Socket socket;
    private int port = 40000;
    private String host;
    private String user;
    private Mediator mediator;
    private BufferedReader in;
    private PrintWriter out;

}
