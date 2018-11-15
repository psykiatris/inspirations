/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
* This is where the server is defined. It may be GUI in the future, but
* that's a different class file.
* */
public class Listen {
    private static final long serialVersionUID = 1L;




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

    public void log(String msg) {
        /*
         * This will log messages regardomg actions, such as connections
         * to the server, chat, etc. Will be saved to a local log file
         * */
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy " +
                "HH:mm:ss");
        String timeStamp = sdf.format(time);
        // Print to terminal
        // Will add file options later
        System.out.println(timeStamp + msg + "\n");
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
        * */
    }



    public static void main(String[] args) {
        int port = 2222;
        boolean listening = true;

        // try-with-resources
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            // Endless loop
            while(listening) {
                new ListenThread(serverSocket.accept()).start();

            }

        } catch (IOException e) {
            System.out.println("Error when trying to listen on port " + port + " or connecting to client.");

        }

    }

}

