/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.ServerSocket;


/*
* This is the server, whidch only istends to the port. When it receives
* connection, it will pass to ListenThread, which handles the
* communication.
* */
public class Listen {
    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        int port = 2222;
        boolean listening = true;

        // try-with-resources
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("The server has started on port " + port);

            // Endless loop
            while(listening) {
                new ListenThread(serverSocket.accept()).start();

            }

        } catch (IOException e) {
            System.out.println("Error when trying to listen on port " + port + " or connecting to client.");

        }

    }

}

