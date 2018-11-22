/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* This is copied from Oracle's Tutorial, in an effort to understand how
* using a terminal would work. Sds it is, only one client can connect at
 * a time.
* */
public class EchoServer {
    public static void main(String[] args) throws IOException {

        // Pass arguments from command-line
        if(args.length != 1) {
            System.err.println("Usage: EchoServer <port number>");
            System.exit(2);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        /* Multiple connections, get passed off to threads.*/
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            log("Starting server");

            while(listening) {

                new Thread(new EchoThread(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            System.out.println("Could not listen to port " +
                    "to port " + portNumber);
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void log(String s) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = sdf.format(time);
        System.out.printf("[%s]: %s%n", timeStamp, s);
    }


}
