/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.*;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/*
* The aim of this class is to listen for connections and pass the
* channel to a new thread.
* */
public class ChannelServer {
    private static InetAddress hostIPAddr;
    private static Selector selector;
    private static ServerSocketChannel ssChannel;




    public static void main(String[] args) {
        // Pass in params from command line
        //Get port number
        if(args.length != 1) {
            System.out.println("Usage: ChannelServer <port number>");
            System.exit(100);
        }

        try {
            hostIPAddr = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            TheServer.log("Unknown host exception");
        }
        int port = Integer.parseInt(args[0]);
        try {
            /* When debugging, program stops here. It assigns an
            EPollSelector, so appears not to be correct.
            Otherwise, program runs with no exceptions. It just doesn't
            get to the thread...*/
            selector = Selector.open();
        } catch (IOException e) {
            TheServer.log("IO error with selector");
        }
        try {
            ssChannel = ServerSocketChannel.open();
            // Non-blocking
            ssChannel.configureBlocking(false);
            //Get host info
            ssChannel.socket().bind(new InetSocketAddress(hostIPAddr,
                    port));
            // Register selector
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (ClosedChannelException e) {
            TheServer.log("ServerSocket Channel closed unexpectedly");
        } catch (IOException e1) {
            TheServer.log("IO error with serverSocket");
        }
        while(true) {
            try {
                if(selector.select() <= 0) {
                    continue;
                }
            } catch (IOException e) {
                TheServer.log("IO error while processing the selector");
            }
            // This method will pass info to thread.
            processCon(selector.selectedKeys());
        }

    }

    public static void processCon(Set readySet) {
        Iterator itr = readySet.iterator();
        while(itr.hasNext()) {
            SelectionKey key = (SelectionKey) itr.next();
            itr.remove();
            if(key.isAcceptable()) {
                //Pass it on
                try (ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel()) {
                    SocketChannel sChannel =
                            (SocketChannel) ssChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(key.selector(),
                            SelectionKey.OP_READ);
                    TheServer.log("Passing connection");
                    new Thread(new ChannelThread(sChannel, selector));
                } catch (IOException e) {
                    TheServer.log("IO error getting key.channel()");
                }
            }
        }

    }


}


