/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ListenThread extends Thread {
    private Socket socket = null;

    public ListenThread(Socket s) {
        super("ListenThread");
        socket = s;

    }

    public void run() {


        try(PrintWriter out = new PrintWriter(socket.getOutputStream(),
                true); BufferedReader in =
                new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String input, output;
            Mediator mediate = new Mediator();
            output = mediate.processIn("");
            out.println(output);

            while ((input = in.readLine()) != null) {
                output = mediate.processIn(input);
                out.println(output);
                if(output.equals("Bye"))
                    break;
            }
            socket.close();

        } catch (IOException e) {
            //log(e.getMessage());
        }
    }
}
