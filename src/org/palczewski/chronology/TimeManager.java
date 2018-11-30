/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.chronology;

import javax.swing.plaf.basic.BasicMenuUI;
import java.time.*;
import java.time.chrono.*;
import java.time.format.ResolverStyle;
import java.time.temporal.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/*
* The goal of this class is to utilize the new Date-Time API that was
* releases in JDK8, with the java.time packages. This file will attempt
* to develop a custom-clock for a future multi-player game.
* */
public class TimeManager {


    TimeManager() {



    }


    public static void main(String[] args) {

        Clock clock = Clock.systemDefaultZone();

        /*
         * This allows me to use a clock object for timeZone reference.
         *
         * Will need to manage ticking the days, as the hours and minutes
         * will be curent.
         *
         * */
        LocalDateTime dateTime =
                LocalDateTime.now(clock).withYear(2020).withMonth(9).withDayOfMonth(1);

        LocalDateTime curTime = LocalDateTime.now();

        System.out.println("Game time: " + dateTime);
        System.out.println("Time now: " + curTime);

        /*
        * In order to tick time, have a thread loop where the Clock
        * increments 1 minut for every 15 seconds. It will update the
        * time varialble with the ticked time, which the user can
        * request when they want.
        * // TODO: 11/30/18 Set up thread to tick time.
         * */
        System.out.println("Adjusted: " + dateTime.plusHours(24));
    }
}
