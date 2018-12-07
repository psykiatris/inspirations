/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkIF {

    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> e =
                    NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements()) {
                NetworkInterface nif = e.nextElement();
                System.out.printf("Name: %s, Supports Multicast: %s, " +
                        "isUp(): %s%n", nif.getName(),
                        nif.supportsMulticast(), nif.isUp());

            }
        } catch (SocketException e1) {
            System.out.println("Socket error getting Interface");
        }

    }
}
