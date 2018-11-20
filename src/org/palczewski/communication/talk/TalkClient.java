/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TalkClient implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int PORT_NUMBER = 2222;
    private String name = "Name";
    private String host = "localhost";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    TalkClient() {

        new Thread(this, "Client").start();

    }


    public static void main(String[] args) {
        new TalkClient();


    }

    public void run() {
        // Connect to server
        try {
            socket = new Socket(host, PORT_NUMBER);
            // Set up in/ou to server
            in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            // Process input
            String input = in.readLine();

            System.out.println("Your name is: " + name);

        } catch (UnknownHostException e) {
            System.out.println("Host is unknown.");
        } catch (ConnectException e) {
            System.out.println("The server is not running.");
        } catch (IOException e) {
            System.out.println("IO error in Client.run()");
        }

    }

    private void send() {
        /*
        * Create a buffer to recive input from System.in. May not need,
        * as there will be a Comm class to handle this.
        * */
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            String message = stdIn.readLine();
            if(message.length() > 0) {
                System.out.println(message);
            }

        } catch (IOException e) {
            System.out.println("IO Error in Client.send()");
        }


    }

    private void login() {
        // Set up a buffer
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter your name: ");
        try {
            name = stdIn.readLine();
        } catch (IOException e) {
            System.out.println("IOError in Client.login()");
        }

    }
}