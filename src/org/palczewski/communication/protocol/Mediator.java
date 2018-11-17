/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.protocol;

import org.palczewski.communication.Protocol;
import org.palczewski.communication.listen.Listen;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
* This is the go-between (protocol) between Listen and Talk, to relay
* messages back
 * and forth.
 * */
public class Mediator implements Runnable {

    private static final String DEFAULT_NAME = "(New Client)";
    private Listen server;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private String name = DEFAULT_NAME;

    public Mediator(Listen listen, Socket skt) {
        server = listen;
        socket = skt;
        new Thread(this).start();
    }

    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            // This causes an exception.
            sendToTalk(Protocol.SUBMIT);

            boolean validName = false;

            boolean keepRunning = true;

            while(keepRunning) {
                String input = in.readLine();
                server.log(input + " received from " + name);
                if(input == null)
                    keepRunning = false;

            }


        } catch (IOException e) {
            server.log("IO error occurred in Mediator");
            server.log(e.getMessage());
        } finally {
            quit();
        }
    }

    private void quit() {
        server.log("Session ended for " + name);
        try {
            socket.close();
        } catch (IOException e) {
            server.log("Error closing socket");
        }
    }

    public void sendToTalk(String s) {
        out.println(s);
        server.log(s + " was sent to " + name);
    }

}
