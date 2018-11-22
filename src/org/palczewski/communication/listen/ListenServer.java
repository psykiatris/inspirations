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
import java.util.HashSet;


/*
* This class will listen for incoming connections, and creates threads
* for each one.
* */
public class ListenServer extends Thread {
    private static final long serialVersionUID = 1L;
    private static final int port = 2222;
    // To keep track of clients
    private static HashSet<String> names = new HashSet<>();
    // Keep track of writers
    private static HashSet<PrintWriter> writers = new HashSet<>();

    ListenServer() {

    }



    public static void main(String[] args) {
        log("Starting server");



    }

    public void run() {
        
    }

    // Write log messages
    public static void log(String msg) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = sdf.format(time);
        System.out.printf("[%s]: %s%n", timeStamp, msg);
    }



}

