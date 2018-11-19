/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import protocol.Mediator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
This is the main point for the server. It simply listens for connections
. When they are made, it passes it off to the server Thread.
* */
public class Listen implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int PORT_NUMBER = 2222;
    private final ArrayList<Mediator> connections = new ArrayList<>();


    Listen() {
        startListen();

    }

    public boolean addConnection(Mediator m, String newName) {
        boolean added = false;
        boolean found = false;

        synchronized(connections) {
            // loop thru connections
            for(int i = 0; i< connections.size() && !found; i++) {
                Mediator connection = connections.get(i);
                String name = connection.getName();
                if (newName.equals(name)) {
                    found = true;

                }
            }
            if(!found) {
                connections.add(m);
                added = true;
            }

        }
        return added;
    }

    public void removeConnection(String removeName) {
        // This removes the name from the list
        boolean found = false;

        synchronized(connections) {
            //Loop
            for(int i = 0; (i < connections.size()) && !found; i++) {
                Mediator connection = connections.get(i);
                String name = connection.getName();
                if(removeName.equals(name)) {
                    found = true;
                    connections.remove(i);
                }
            }
        }

    }

    private void startListen() {
        new Thread(this).start();

    }


    public void run() {
        log("Server is running.");

        try(ServerSocket server = new ServerSocket(PORT_NUMBER)) {
            boolean keepServer = true;

            do {
                Socket socket = server.accept();
                log("New connection started");
                new Mediator(this, socket);

            } while (keepServer);

        } catch (IOException e) {
            log("An exception was caught while listening to port " + PORT_NUMBER);
            log(e.getMessage());
        }
    }

    public void log(String msg) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = sdf.format(time);
        System.out.printf("[%s]: %s%n", timeStamp, msg);
    }

    public void broadcast(String s) {

        synchronized(connections) {
            for (Mediator connection : connections) {
                connection.sendToTalk(s);
            }
        }
    }

    public static void main(String[] args) {
        new Listen();

    }
}