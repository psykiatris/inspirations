/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import org.palczewski.communication.listen.ListenServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
* The example this class is taken from is based on swing (GUI). Writing
* this to be used with a terminal
* */
public class TalkClient {

    BufferedReader in;
    PrintWriter out;

    public TalkClient() {

    }

    private String getServerAddress() {
        // Set up Input from console
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter server address: ");
        try {
            return stdIn.readLine();
        } catch (IOException e) {
            ListenServer.log("Error in getting server address");
        }
        return null;
    }

    private String getName() {
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
        try {
            return stdIn.readLine();
        } catch (IOException e) {
            ListenServer.log("Error getting username.");
        }
        return null;

    }

    // Connect to server
    private void run() throws IOException {
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 2222);
        in =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String user = getName();

        // Now process input
        while(true) {
            String line = in.readLine();
            if(line.startsWith("SUBMITNAME")) {
                out.println(user);

            } else if(line.startsWith("NAMEACCEPTED")) {
                BufferedReader stdIn =
                        new BufferedReader(new InputStreamReader(System.in));
                System.out.print("[" + user + "]: ");
            } else if(line.startsWith("MESSAGES")) {
                System.out.println(line.substring(8));
            }

        }
    }

    public static void main(String[] args) {
        TalkClient client = new TalkClient();
        try {
            client.run();
        } catch (IOException e) {
            System.out.println("Error running Client");
        }
    }
}
