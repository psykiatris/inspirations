/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;


import org.palczewski.communication.Protocol;
import org.palczewski.communication.listen.Listen;

import javax.swing.text.DefaultCaret;
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
        System.out.println("Connecting to server at " + host + "\nvia " +
                "port " + PORT_NUMBER);
        System.out.println();

        try (Socket socket = new Socket(host, PORT_NUMBER); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your user name: ");
            String userName = stdIn.readLine();

            boolean keepRunning = true;

            while(keepRunning) {

                String input = in.readLine();
                if(input == null) {
                    keepRunning = false;
                } else if(input.length() > 0) {
                    String pCode = input.substring(0, 1);
                    String rest = input.substring(1);

                    switch(pCode) {
                        case Protocol
                                .SUBMIT:
                            out.println(Protocol.NAME + userName);
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
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            String msg = br.readLine().trim();
            if(!msg.isEmpty())
                System.out.println(msg);


        } catch (IOException e) {
            System.out.println("Error reading from console.");
        }
    }


    public static void main(String[] args) {
        new Talk();

    }
}
