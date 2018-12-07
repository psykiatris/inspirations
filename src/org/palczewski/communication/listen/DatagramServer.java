/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramServer {

    public static void main(String[] args) {

        try (DatagramChannel server = DatagramChannel.open()) {
            InetSocketAddress sAddress = new InetSocketAddress(
                    "localhost", 2222);
            server.bind(sAddress);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(true) {
                System.out.println("Waiting for a message from a remote " +
                        "host at " + sAddress);
                SocketAddress rAddr = server.receive(buffer);
                buffer.flip();
                int limits = buffer.limit();
                byte bytes[] = new byte[limits];
                buffer.get(bytes, 0, limits);
                String msg = new String(bytes);
                System.out.println("Client at " + rAddr + " says: " + msg);
                buffer.rewind();
                server.send(buffer, rAddr);
                buffer.clear();
            }
        } catch (IOException e) {
            System.out.println("IO error with DatagramChannel");
        }


    }
}
