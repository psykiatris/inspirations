/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import org.palczewski.communication.protocol.Mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ListenThread extends Thread {
    private Socket socket;
    String aName; // Hanle connection names


    public ListenThread(Socket s) {
        super("ListenThread");
        socket = s;
        aName = "Connection"

    }

    public void broadcast(String msg) {
        /*
         * This sends all messages from server to all clients.
         * */
    }

    public void addConnection() {
        /*
         * This will add incoming connections to a list, to track
         * connections and reject duplicates.
         * */
    }


    public void removeConnection() {
        /*
         * This will remove connections from the list, when a user
         * disconnects.
         * */
    }

    public void showConnections() {
        /*
         * This will display a list of connections to the other users, so
         * that they may know who is online
         *
         * Cretes a mew tjread tp jamd;e cpmmectopms frp, multiple clients.
         * */
    }

    public void requestLogin() {
        /*
         * Talk (client) will log in via this method, which will then
         * store connections, and notiy everyone.
         *
         * */
    }

    public void run() {


        try(PrintWriter out = new PrintWriter(socket.getOutputStream(),
                true); BufferedReader in =
                new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Mediator mediate = new Mediator();
            mediate.log("New connection established");
            String input, output;
            output = mediate.processIn("Welcome to no brains chat");
            out.println(output);

            while ((input = in.readLine()) != null) {
                output = mediate.processIn(input);
                out.println(output);
                if(output.equals("Bye"))
                    break;
            }
            socket.close();

        } catch (IOException e) {
            //log(e.getMessage());
        }
    }
}
