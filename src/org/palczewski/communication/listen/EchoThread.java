/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import org.palczewski.communication.protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
* This class sets up the threads for each connection to the server
* */
public class EchoThread extends Thread {
    private Socket socket = null;
    EchoServer server;

    public EchoThread(Socket socket) {
        super("EchoThread");
        this.socket = socket;
    }

    public void run() {
        EchoServer.log("Established new connection");

        // Open the streams
        try(PrintWriter out = new PrintWriter(socket.getOutputStream(),
                true);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String inputLine, outputLine;
            // Create Mediator object
            Protocol protocol = new Protocol();
            outputLine = protocol.processInput(null);
            out.println(outputLine);

            while((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine);
                out.println(outputLine);
                if(outputLine.equals("Bye"))
                    break;
            }
            socket.close();

        } catch (IOException e) {
            EchoServer.log("IO Error in EchoThread.run()");
            EchoServer.log(e.getMessage());
        }

    }
}
