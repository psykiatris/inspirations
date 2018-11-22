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

        // Try-with-resources so it will
        // autoclose
        try (
                ServerSocket serverSocket =
                        new ServerSocket(Integer.parseInt(args[0]));
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(),
                                true);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen " +
                    "to port " + portNumber+ " or listening for a " +
                    "connection");
            System.out.println(e.getMessage());
        }
    }

}
