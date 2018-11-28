/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/*
* Copied from Oracle's tutorial to better understand using terminal to
* communicate to server.
* */
public class TheClient {
    public static void main(String[] args) {

        if(args.length != 2) {
            System.err.println("Usage: TheClient <host> <port number>");
            System.exit(2);
        }

        // Get name info from user
        String user = login();

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(),
                                true);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {
            // Console input buffer
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));

            String fromUser;
            String fromServer;

            // Listening for server traffic
            while((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                // If server notifies it will quit.
                if(fromServer.equalsIgnoreCase("Bye"))
                    break;

                fromUser = stdIn.readLine();

                // Watch for user input
                if(fromUser != null) {
                    System.out.println(user + ": " + fromUser);
                    out.println(fromUser);
                }

            }

        } catch (UnknownHostException e) {
            System.out.println("Could not reach host at " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Couldn't get IO for connection to " + hostName);
            System.exit(1);
        }

    }

    private static String login() {
        System.out.print("Please enter your name: ");
        // Open stream
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            return stdIn.readLine();
        } catch (IOException e) {
            System.out.println("IO Error getting name");
        }
        return null;

    }
}
