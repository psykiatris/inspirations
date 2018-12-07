/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

import static java.nio.channels.DatagramChannel.open;

/*
* A multicast client using Datagram
* */
public class MulticastClient {
    public static final String MULTICAST_IP = "localhost";
    public static final int MULTICAST_PORT = 2222;
    public static final String MULTICAST_INTERFACE_NAME = "eth1";

    public static void main(String[] args) {
        MembershipKey key = null;
        ByteBuffer buffer;
        try (DatagramChannel client = open(StandardProtocolFamily.INET)) {
            NetworkInterface interf =
                    NetworkInterface.getByName(MULTICAST_INTERFACE_NAME);
            client.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            client.bind(new InetSocketAddress(MULTICAST_PORT));
            client.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);

            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            key = client.join(group, interf);
            System.out.println("Joined the multicast group: " + key);
            System.out.println("Waiting for a message from the multicast " +
                    "group...");

            buffer = ByteBuffer.allocate(1048);
            client.receive(buffer);
            buffer.flip();
            int limits = buffer.limit();
            byte bytes[] = new byte[limits];
            buffer.get(bytes, 0, limits);
            String msg = new String(bytes);

            System.out.printf("Multicast message: %s%n", msg);
            key.drop();
        } catch (IOException e) {
            System.out.println("IO error with datagram");
        }

    }

}
