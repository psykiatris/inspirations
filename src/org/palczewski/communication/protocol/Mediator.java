/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.communication.protocol;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* This is the go-between (protocol) between Listen and Talk, to relay
* messages back
 * and forth.
 * */
public class Mediator {

    public void log(String msg) {
        /*
        * This method manages logging system info to either server or
        * client.
        * */
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy " +
                "HH:mm:ss");
        String timeStamp = sdf.format(time);
        // Print to terminal
        // Will add file options later
        System.out.println(MessageFormat.format("[{0}]: {1}", timeStamp,
                msg));
    }


    public String processIn(String msg) {

        return msg;
    }


}
