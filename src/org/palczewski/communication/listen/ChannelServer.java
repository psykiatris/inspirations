/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

/*
* The aim of this class is to listen for connections and pass the
* channel to a new thread.
* */
public class ChannelServer {




    public static void main(String[] args) {


        if(args.length != 1) {
            System.out.println("Usage: ChannelServer <port number>");
            System.exit(100);
        }


        int port = Integer.parseInt(args[0]);
        boolean listening = true;

        try(ServerSocketChannel server =  ServerSocketChannel.open()) {
            TheServer.log("Channel server starting");

            while(listening) {
                server.socket().bind(new InetSocketAddress(port));


                // Pas it along
                new Thread(new ChannelThread(server.accept()));



            }

        } catch (IOException e) {
            TheServer.log("IO exception in ChannelServer");
        }
    }


}


