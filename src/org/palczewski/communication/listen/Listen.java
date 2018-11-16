/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import org.palczewski.communication.protocol.Mediator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;


/*
This is the main point for the server. It simply listens for connections
. When they are made, it passes it off to the server Thread.
* */
public class Listen implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int PORT_NUMBER = 2222;


    Listen() {
        startListen();

    }

    private void startListen() {
        new Thread(this).start();

    }


    public void run() {
        log("Server is running.");

        try(ServerSocket server = new ServerSocket(PORT_NUMBER)) {

            while(true) {
                Socket socket = server.accept();
                log("New connection started");
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream());
                out.println("Welcome to this server");
            }
        } catch (IOException e) {
            log("An exception was caught while listening to port " + PORT_NUMBER);
            log(e.getMessage());
        }
    }

    public void log(String msg) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = sdf.format(time);
        System.out.printf("[ %s ]: %s%n", timeStamp, msg);
    }

    public static void main(String[] args) {
        new Listen();



    }



}

