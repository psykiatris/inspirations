/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import org.palczewski.communication.protocol.Mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
* This is the client. It will use Mediator to communicate with Listen
* (server) and other talkers (clients)
*
* Will log in with the server, etc.
* */
public class Talk {
    private static final long serialVersionUID = 1L;
    private Socket socket;
    private String user;
    private Mediator mediator;
    private BufferedReader in;
    private PrintWriter out;

    public static void main(String[] args) {

        String hostName = "localhost";
        int port = 2222;

        try(Socket socket = new Socket(hostName, port); PrintWriter out
                = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("Bye"))
                    break;

                fromUser = stdIn.readLine();
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown host " + hostName);
        } catch (IOException e) {
            System.out.println("Error connecting to " + hostName);
            System.exit(1);
        }
    }

}
