/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
* Copied from Oracle's tutorial to better understand using terminal to
* communicate to server.
* */
public class EchoClient {
    public static void main(String[] args) {

        if(args.length != 2) {
            System.err.println("Usage: EchoClient <host> <port number>");
            System.exit(2);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(),
                                true);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(new InputStreamReader(System.in))

                ) {
            String userInput;
            while((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }

        } catch (UnknownHostException e) {
            System.out.println("Could not reach host at " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Couldn't get IO for connection to " + hostName);
            System.exit(1);
        }


    }
}
