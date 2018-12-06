/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/*
* Found an example on the internet for using Java's NIO channels for
* networking.
* */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        // Multiplexes channel objects
        Selector selector = Selector.open();

        // Open a server socket
        ServerSocketChannel serverSocket = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("localhost",
                2222);

        // Bind server to address
        serverSocket.bind(address);


        // Adjust
        serverSocket.configureBlocking(false);


        int ops = serverSocket.validOps();
        // Register connection
        SelectionKey selectKey = serverSocket.register(selector, ops,
                    null);

        //Infinite loop
        //Keep server running
        TheServer.log("Server is up and waiting...");
        while(true) {


            selector.select();

            // token representing channel
            Set<SelectionKey> serverKeys = selector.selectedKeys();
            Iterator<SelectionKey> keysItr = serverKeys.iterator();

            while(keysItr.hasNext()) {
                SelectionKey myKey = keysItr.next();

                // Test if key is ready to accept a new socket
                if(myKey.isConnectable())  {
                    SocketChannel client = null;
                    client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    TheServer.log("Connection accepted: " + client.getLocalAddress() + "\n");

                } else if(myKey.isReadable()) {
                    SocketChannel client = (SocketChannel) myKey.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String result = new String(buffer.array()).trim();
                        TheServer.log("Message received: " + result);
                        if (result.equals("Crunchify")) {
                            client.close();
                            TheServer.log("Closing connection to client");

                        }
                }
                keysItr.remove();
            }

        }


    }
}
