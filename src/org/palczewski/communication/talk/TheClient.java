/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/*
* Copied from Oracle's tutorial to better understand using terminal to
* communicate to server.
* */
public class TheClient {
    static BufferedReader stdIn;

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
                                true, StandardCharsets.UTF_8);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
                ) {
            // Console input buffer
            stdIn =
                    new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

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
                    System.out.println(MessageFormat.format("{0}: {1}", user, fromUser));
                    out.println(fromUser);
                }

            }

        } catch (UnknownHostException e) {
            System.out.println(MessageFormat.format("Could not reach host at {0}", hostName));
            System.exit(1);
        } catch (IOException e) {
            System.out.println(MessageFormat.format("Couldn''t get IO for connection to {0}", hostName));
            System.exit(1);
        }

    }

    private static String login() {
        System.out.print("Please enter your name: ");

        stdIn =
                new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        try {
            return stdIn.readLine();
        } catch (IOException e) {
            System.out.println("IO Error getting name");
        }
        return null;

    }
}
