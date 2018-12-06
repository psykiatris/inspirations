/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.talk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

public class AsyncChannelClient {

    public static void main(String[] args) throws Exception {

        AsynchronousSocketChannel channel =
                AsynchronousSocketChannel.open();
        SocketAddress serverAddr = new InetSocketAddress("localhost",2222);
        Future<Void> result = channel.connect(serverAddr);
        result.get();
        System.out.println("Connected");
        Attachment attach = new Attachment();
        attach.channel = channel;
        attach.buffer = ByteBuffer.allocate(2048);
        attach.isRead = false;
        attach.mainThread = Thread.currentThread();

        Charset cs = Charset.forName("UTF-8");
        String msg = "Hello";
        byte[] data = msg.getBytes(cs);
        attach.buffer.put(data);
        attach.buffer.flip();

        ReadWriteHandler readWriteHandler = new ReadWriteHandler();
        channel.write(attach.buffer, attach, readWriteHandler);
        attach.mainThread.join();
    }
}

class Attachment {
    AsynchronousSocketChannel channel;
    ByteBuffer buffer;
    Thread mainThread;
    boolean isRead;

}

class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    public void completed(Integer result, Attachment attach) {
        if(attach.isRead) {
            attach.buffer.flip();
            Charset cs = Charset.forName("UTF-8");
            int limits = attach.buffer.limit();
            byte bytes[] = new byte[limits];
            attach.buffer.get(bytes, 0, limits);
            String msg = new String(bytes, cs);
            System.out.printf("Server responded: %s%n", msg);
            try {
                msg = this.fromUser();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if(msg.equalsIgnoreCase("bye")) {
                attach.mainThread.interrupt();
                return;
            }
            attach.buffer.clear();
            byte[] data = msg.getBytes(cs);
            attach.buffer.put(data);
            attach.buffer.flip();
            attach.isRead = false;
            attach.channel.write(attach.buffer, attach, this);

        }
    }
    public void failed(Throwable e, Attachment attach) {
        e.printStackTrace();

    }
    private String fromUser() throws Exception {
        System.out.print("Please enter a message (bye to quit)");
        BufferedReader console =
                new BufferedReader(new InputStreamReader(System.in));
        String msg = console.readLine();
        return msg;
    }

}