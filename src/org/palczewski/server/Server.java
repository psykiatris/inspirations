package org.palczewski.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikalsh
 */
public class Server {

    private ServerSocketChannel server;
    private List<SocketChannel> clients;
    private SelectionKey selectorKey;
    private Selector selector;
    private ByteBuffer buffer;

    public Server(int port) throws IOException, InterruptedException, ExecutionException {
        clients = new ArrayList<>();

        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("localhost", port));
        server.configureBlocking(false);
        selectorKey = server.register(selector, SelectionKey.OP_ACCEPT);
        buffer = ByteBuffer.allocate(256);

        acceptAndProcessIOLoop();
    }

    private void acceptAndProcessIOLoop() {
        /**
         * main loop of server. note that the acceptor and IO happens in the
         * same single thread
         */
        while (server.isOpen()) {
            try {
                selector.select();

                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iter = keySet.iterator();

                /**
                 * iterate through all keys
                 */
                while (iter.hasNext()) {

                    SelectionKey key = iter.next();

                    /**
                     * acceptor - equivalent of a client connection listener
                     */
                    if (key.isAcceptable()) {
                        System.out.println("new client connected: " + selector);
                        accept(selector, server);
                    }

                    /**
                     * currently all I/O happens here. currently we just want to
                     * write to all socket channels as soon as we have a
                     * readable key ready
                     */
                    if (key.isReadable()) {
                        readAndWrite(buffer, key);
                    }
                    iter.remove();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listAllClients() {
        System.out.println("connections#: " + clients.size());
    }

    /**
     * accepts and creates a socketchannel to this serverSocket and add it to
     * our arraylist of socketchannels. configure it for non-blocking and
     * register it to our selector with interest set READ and WRITE
     *
     * @param selector
     * @param serverSocket
     * @throws IOException
     */
    private void accept(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        clients.add(client);

        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        listAllClients();
    }

    /**
     * get the channel from the selected readable key, read its buffer, flip it
     * then write it to each socketchannel in our clients list
     *
     * @param buffer
     * @param key
     * @throws IOException
     */
    private void readAndWrite(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel thisClient = (SocketChannel) key.channel();
        thisClient.read(buffer);

        System.out.println(new String(buffer.array()).trim());

        buffer.flip();
        /**
         * important to rewind the buffer, otherwise we're just writing an
         * empty buffer after the the first iteraton
         */
        for (SocketChannel client : clients) {
            client.write(buffer);
            buffer.rewind();
        }
        buffer.clear();
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        int port = (args.length > 0 ? Integer.parseInt(args[0]) : 5454);
        Server sl = new Server(port);
    }
}
