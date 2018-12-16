package org.palczewski.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikalsh
 */
public class Client {

    private Socket serverConnection;
    private String input = "";
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private BufferedReader cin;
    /**
     * name and randName for testing purposes
     */
    String name;
    String[] randName = {"client1", "client2", "client3", "client4"};

    public Client(String host, int port) throws IOException, InterruptedException {
        
        name = randName[new Random().nextInt(randName.length)];

        try {
            System.out.println("trying to connect to: " + host + ":" + port);
            serverConnection = new Socket(host, port);
            System.out.println("connected to server " + host + ": " + port);

            cin = new BufferedReader(new InputStreamReader(System.in));
            toServer = new PrintWriter(new OutputStreamWriter(serverConnection.getOutputStream(), "UTF-8"), true);
            fromServer = new BufferedReader(new InputStreamReader(serverConnection.getInputStream(), "UTF-8"));
            readLoop();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException exa) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, exa);
        }
    }

    private void readLoop() throws IOException, InterruptedException {
        Thread reader = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(fromServer.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        reader.start();

        /**
         * write loop
         */
        while ((input = cin.readLine()) != null) {

            toServer.println(name + ": " + input);
            toServer.flush();

            if (input.equalsIgnoreCase("bye")) {
                fromServer.close();
                cin.close();
                serverConnection.close();
                System.out.println("disconnecting..");
                reader.interrupt();
            }
        }
        System.out.println("connection closed");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String host;
        int port;
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);

        } else {
            host = "localhost";
            port = 5454;
        }
        Client client = new Client(host, port);
    }
}
