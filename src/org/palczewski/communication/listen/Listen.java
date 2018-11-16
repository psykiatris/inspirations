/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import org.palczewski.communication.protocol.Mediator;

import java.io.IOException;
import java.net.ServerSocket;


/*
This is the main point for the server. It simply listens for connections
. When they are made, it passes it off to the server Thread.
* */
public class Listen {
    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        int port = 2222;
        /*
        * This flag will keep server running. It's a private variable,
        * unless I will have a different process to shuytdown the server
         * (such as if maintenance or anything else occurs).
         * */
        private boolean listening = true;

        // try-with-resources
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            Mediator m = new Mediator();

            m.log("The server has started on port " + port);

            // Endless loop
            while(listening) {
                // Passes connection to thread.
                new ListenThread(serverSocket.accept()).start();

            }

        } catch (IOException e) {
            System.out.println("Error when trying to listen on port " + port + " or connecting to client.");

        }

    }

}

