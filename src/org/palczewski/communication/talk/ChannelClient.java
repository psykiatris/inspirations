/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/* Client to connect t0 ChannelThread
* */
public class ChannelClient {

    public static void main(String[] args) {
        InetSocketAddress serverAddr = new InetSocketAddress("localhost"
                , 2222);
        try (SocketChannel socket = SocketChannel.open()) {
            System.out.println("Connecting");

            while(true) {



            }
        } catch (IOException e) {
            System.out.println("IO error occured in connecting to host");
        }

    }
}
