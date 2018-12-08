/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.nio.channels.SocketChannel;

/*
* ChannelServer passes the socket to this class for processing read/write
* */
public class ChannelThread extends Thread {

    SocketChannel socket;

    ChannelThread(SocketChannel socket) {
        super("Client");
        this.socket = socket;
    }

    public void run() {
        TheServer.log("Connection made");
    }
}
