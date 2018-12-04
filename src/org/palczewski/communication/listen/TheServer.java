/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/*
* This is the main server class. It receives connections and passes it
* to a thread.
*
* It also logs any server messages, such as when a perosn
* connects/disconnects, etc.
* */
public class TheServer {



    public static void main(String[] args) {



        // Pass arguments from command-line
        if(args.length != 1) {
            System.err.println("Usage: TheServer <port number>");
            System.exit(2);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        /* Multiple connections, get passed off to threads.*/
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            log("Starting the server");

            while(listening) {

                // Passes client connection to new thread
                new Thread(new ClientThread(serverSocket.accept()),
                        "Client").start();
            }
        } catch (IOException e) {
            System.out.println(MessageFormat.format("Could not listen to port to port {0}", portNumber));
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void log(String s) {

        LocalDateTime time = LocalDateTime.now();
        String timeStamp =
                time.format(DateTimeFormatter.ofPattern("MM'/'dd'/'yyyy HH':'mm':'ss"));
        System.out.printf("[%s]: %s%n", timeStamp, s);


    }


}
