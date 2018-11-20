/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
* This class will listen for incoming connections, and creates threads
* for each one.
* */
public class ListenServer implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int PORT_NUMBER = 2222;

    // Construcotr
    ListenServer() {
        new Thread(this, "Server").start();

    }

    public static void main(String[] args) {
        new ListenServer();

    }

    public void log(String msg) {
        // TODO: 11/19/18 Fix to utilize the java.time functions.
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = sdf.format(time);
        System.out.printf("[%s]: %s%n", timeStamp, msg);
    }

    public void run() {

        // Log message at startup
        log("The server is active");



        // Server loop
        // This will auto-close when stopped.
        try(ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {

            boolean runServer = true;
            do {
                // Attach socket to server
                Socket socket = serverSocket.accept();

                log("Starting new connection.");
            } while (runServer);

        } catch (IOException e) {
            log("Error listening on port " + PORT_NUMBER);
            log(e.getMessage());
        }


    }


}
