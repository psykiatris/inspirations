/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
* Uses datagram to multicast
* */
public class MulticastServer {
    public static final String MULTICAST_IP = "192.168.0.1";
    public static final int MULTICAST_PORT = 2222;
    public static final String MULTICAST_INTERFACE_NAME = "wlp2s0";

    public static void main(String[] args) {

        try (DatagramChannel server = DatagramChannel.open()) {
            server.bind(null);
            NetworkInterface interf =
                    NetworkInterface.getByName(MULTICAST_INTERFACE_NAME);
            server.setOption(StandardSocketOptions.IP_MULTICAST_IF,
                    interf);
            String msg = "Hello";
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            InetSocketAddress group =
                    new InetSocketAddress(MULTICAST_IP, MULTICAST_PORT);
            server.send(buffer, group);
            System.out.println("Sent the multicast message: " + msg);

        } catch (IOException e) {
            System.out.println("IO error with datagram channel");
        }

    }
}

