/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import org.palczewski.communication.protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
* This class sets up the threads for each connection to the server
* */
public class ClientThread extends Thread {

    private Socket socket = null;
    TheServer server;

    public ClientThread(Socket socket) {
        super("ClientThread");
        this.socket = socket;
    }

    public void run() {
        TheServer.log("Established new connection");

        /*
        * Develop a log-in type system to get name from client. Will
        * used to manage connections in a HashMap or ArrayList.
        * */



        // Open the streams
        try(PrintWriter out = new PrintWriter(socket.getOutputStream(),
                true);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String inputLine, outputLine;
            // Create Protocol object
            Protocol protocol = new Protocol();
            outputLine = protocol.processInput(null);
            out.println(outputLine);

            while((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine);
                // Echo back user input
                out.println(outputLine);
                // Checks output to see if user quits.
                if(outputLine.equals("Bye"))
                    break;
            }
            // Disconnect from server
            socket.close();

        } catch (IOException e) {
            TheServer.log("IO Error in ClientThread.run()");
            TheServer.log(e.getMessage());
        }

    }

    private void login() {
        /*This method will check the list of connections and ensure
        users cannot log in twice using the same name.*/
    }

    private void addConnection() {
        /*
        * To keep track of the connections, it will process log-in info
        * and creates a list of users currently on-line.*/

    }

    private void removeConnection() {
        /*
        * This method will remove a user when they disconnect from the
        * server with a log message sends a departing message to the
        * other clients.*/
    }
}