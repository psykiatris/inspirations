/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;


import org.palczewski.communication.protocol.Protocol;
import org.palczewski.communication.listen.Listen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
* This is the client. It will use Mediator to communicate with Listen
* (server) and other talkers (clients)
*
* Will log in with the server, etc.
* */
public class Talk implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int PORT_NUMBER = 2222;
    private String name = "Name";
    private String host = "localhost";
    Listen server;



    Talk() {
        new Thread(this).start();

    }

    public void run() {

        try (Socket socket = new Socket(host, PORT_NUMBER); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String userName = login();
            name = userName;


            boolean keepRunning = true;

            // Loop to process input from server
            while(keepRunning) {
                String input = in.readLine();
                if(input == null) {
                    System.out.println("The server has closed.");
                    keepRunning = false;
                } else if(input.length() > 0) {
                    String pCode = input.substring(0, 1);
                    String rest = input.substring(1);

                    switch(pCode) {
                        case Protocol
                                .SUBMIT:
                            out.println(Protocol.NAME + name);
                        break;
                        case Protocol.ACCEPTED:
                            System.out.println("You have logged in.");
                            break;
                        case Protocol.CHAT:
                            System.out.println(rest);
                            break;
                        case Protocol.REJECTED:
                            System.out.println("That name is not " +
                                    "available. Please use another.");
                            userName = login();
                            out.println(Protocol.NAME + userName);
                            break;

                            default:
                                break;

                    }


                }

            }
                    } catch(UnknownHostException e){
                System.out.println("The host is unknown");
            } catch(ConnectException e){
                System.out.println("The server is not running.");
            } catch(IOException e){
                System.out.println("An IO exception occurred connecting to: " + host);
            }


    }

    private void send() {
        try {
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            Socket socket = new Socket(host, PORT_NUMBER);
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            System.out.print("["+ name + "]: ");
            String msg = stdIn.readLine();
            out.println(Protocol.BROADCAST + msg);
        } catch (IOException e) {
            server.log("Error with sending message.");
        }

    }

    private String login() {

            try {
                BufferedReader stdIn =
                        new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Please enter a name: ");
                return stdIn.readLine();
            } catch (IOException e) {
            server.log("Error with logging in.");
        }
        return null;
    }

    public static void main(String[] args) {
        new Talk();

    }
}
