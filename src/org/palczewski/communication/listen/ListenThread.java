/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import org.palczewski.communication.protocol.Mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
/*
* This class receives the connection handed off from Listen and creates
* a thread.
* */

public class ListenThread extends Thread {
    private Socket socket;
    String connection; // Hanle connection names
    Mediator m;



    ListenThread(Socket s) {
        super("Client");
        socket = s;


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

            /*
            * for some reason, chaning this to the variable m, causes a
            * NullPointer
             * Exception.*/
            mediate.log("New connection established");

            String input, output;
            output = mediate.processIn("Welcome to no brains chat");
            out.println(output);
            output = mediate.processIn("Please submit your name: ");
            input = in.readLine();
            output = mediate.processIn("So your name is " + input);

            while ((input = in.readLine()) != null) {
                // This echoes back to client
                output = mediate.processIn(input);
                out.println(output);
                if(output.equals("Bye"))
                    break;
            }
            socket.close();

        } catch (IOException e) {
            m.log(e.getMessage());
        }
    }
}
