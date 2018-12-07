/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;
/*
* Basic client using DatagramChannel
* */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramClient {

    public static void main(String[] args) {
        try {
            try (DatagramChannel client = DatagramChannel.open()) {
                client.bind(null);
                String msg = "Hello";
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                InetSocketAddress sAddr = new InetSocketAddress(
                        "localhost", 2222);
                client.send(buffer, sAddr);
                buffer.clear();
                client.receive(buffer);
                buffer.flip();
                int limits = buffer.limit();
                byte bytes[] = new byte[limits];
                buffer.get(bytes, 0, limits);
                String response = new String(bytes);
                System.out.printf("Server responded: %s%n", msg);

            }
        } catch (IOException e) {
            System.out.println("IO Error with Client Datagram");
        }

    }
}
