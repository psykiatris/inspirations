/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*
* This example found on internet uses channels for server-client
* communications
* */
public class NIOClient {

    public static void main(String[] args) throws IOException,
            InterruptedException {

        InetSocketAddress address = new InetSocketAddress("Localhost",
                2222);
        SocketChannel client = SocketChannel.open(address);

        log("Connecting to server");

        ArrayList<String> companies = new ArrayList<>(5);
        companies.add("Facebook");
        companies.add("Twitter");
        companies.add("Google");
        companies.add("IBM");
        companies.add("Crunchify");

        for(String name : companies) {
            byte[] message = new String(name).getBytes(StandardCharsets.UTF_8);
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            log("Sending " + name);
            buffer.clear();

            // Wait
            Thread.sleep(2000);

        }
        client.close();
    }

    private static void log(String m) {
        System.out.println(m);

    }
}
