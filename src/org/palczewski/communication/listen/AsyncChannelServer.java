/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.listen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/*
* Asynchronous connection - server
* */
public class AsyncChannelServer {

    public static void main(String[] args) throws IOException,
            InterruptedException {

        AsynchronousServerSocketChannel server =
                AsynchronousServerSocketChannel.open();
        String host = "localhost";
        int port = 2222;
        InetSocketAddress sAddr = new InetSocketAddress(host, port);
        server.bind(sAddr);
        System.out.printf("Server is listening at %s%n", sAddr);
        Attachment attach = new Attachment();
        attach.server = server;
        server.accept(attach, new ConnectionHandler());
        Thread.currentThread().join();;

    }
}

class Attachment {
    AsynchronousServerSocketChannel server;
    AsynchronousSocketChannel client;
    ByteBuffer buffer;
    SocketAddress clientAddr;
    boolean isRead;
}

class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {
    public void completed(AsynchronousSocketChannel client,
                          Attachment attach) {
        try {
            SocketAddress clientAddr = client.getRemoteAddress();
            System.out.printf("Accepted connection from %s%n", clientAddr);
            attach.server.accept(attach, this);
            ReadWriteHandler rwHandler = new ReadWriteHandler();
            Attachment newAttach = new Attachment();
            newAttach.server = attach.server;
            newAttach.client = client;
            newAttach.buffer = ByteBuffer.allocate(2048);
            newAttach.isRead = true;
            newAttach.clientAddr = clientAddr;
            client.read(newAttach.buffer, newAttach, rwHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void failed(Throwable e, Attachment attach) {
        System.out.println("Failed to accept a connection");
        e.printStackTrace();
    }
}

class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    public void completed(Integer result, Attachment attach) {
        if(result == -1) {
            try {
                attach.client.close();;
                System.out.printf("Stropped listening to the client " +
                        "%s%n", attach.clientAddr);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if(attach.isRead) {
            attach.buffer.flip();
            int limits = attach.buffer.limit();
            byte bytes[] = new byte[limits];
            attach.buffer.get(bytes, 0, limits);
            Charset cs = Charset.forName("UTF-8");
            String msg = new String(bytes, cs);
            System.out.printf("Client at %s says: %s%n",
                    attach.clientAddr, msg);
            attach.isRead = false;
            attach.buffer.rewind();

        } else {
            // Write to the client
            attach.client.write(attach.buffer, attach, this);
            attach.isRead = true;
            attach.buffer.clear();
            attach.client.read(attach.buffer, attach, this);

        }
    }

    public void failed(Throwable e, Attachment attach) {
        e.printStackTrace();
    }
}
