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


    Talk() {
        new Thread(this).start();

    }

    public void run() {


        try (Socket socket = new Socket(host, PORT_NUMBER); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream())) {

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String input = in.readLine();
            out.println(stdIn.readLine());


                    } catch(UnknownHostException e){
                System.out.println("The host is unknown");
            } catch(ConnectException e){
                System.out.println("The server is not running.");
            } catch(IOException e){
                System.out.println("An IO exception occurred connecting to: " + host);
            }

        }

    public static void main(String[] args) {
        new Talk();

    }
}
