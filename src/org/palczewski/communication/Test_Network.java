/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*
* Test NIO - SERVER
* */
public class Test_Network {
    static String host = "localhost";
    static int port = 2222;


    public static void main(String[] args) {

        try (ServerSocketChannel ssChannel = ServerSocketChannel.open(); ServerSocket server = ssChannel .socket(); Selector selector = Selector.open()) {

            ssChannel.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server began listening on port " + port);

            while(true) {
                int num = selector.select();
                if(num == 0)
                    continue;

                Set keys = selector.selectedKeys();
                Iterator it = keys.iterator();
                while(it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    if((key.readyOps() & SelectionKey.OP_ACCEPT) ==SelectionKey.OP_ACCEPT) {
                        int count = 0;
                        Socket client = server.accept();
                        count++;
                        System.out.println("Client connected. You have " + count + " clients connected.");
                        SocketChannel cChannel = client.getChannel();
                        cChannel.configureBlocking(false);
                        cChannel.register(selector, SelectionKey.OP_READ);

                    } else {
                        if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                            SocketChannel client = null;
                            client = (SocketChannel) key.channel();

                        }
                    }
                }
                it.remove();
            }



        } catch (IOException e) {
            System.out.println("IO Error with sockets");
        }

    }



}
