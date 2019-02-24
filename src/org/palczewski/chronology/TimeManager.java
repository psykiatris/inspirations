/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.chronology;


import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/*
* The goal of this class is to utilize the new Date-Time API that was
* releases in JDK8, with the java.time packages. This file will attempt
* to develop a custom-clock for a future multi-player game.
* */
public class TimeManager {


    TimeManager() {
        //Start thread from here.



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
        * increments 1 minute for every 15 seconds. It will update the
        * time varialble with the ticked time, which the user can
        * request when they want.
        * // TODO: 11/30/18 Set up thread to tick time.
         * */
        System.out.println("Adjusted: " + dateTime.plusHours(24));
        System.out.println();

        // Using temporal adjsters.
        LocalDate now = LocalDate.now();
        LocalDate adjusted = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Now with last day of month " + adjusted);
        System.out.println("Now: " + now);
        System.out.println();
        displayTime();

    }

    public static void displayTime() {
        /*
        * The clock loop will update the global time/date variable. This
         * function will display it to th euser whenever desired.
         * */

        // Period define distances in dates
        Period period = Period.between(LocalDate.now(),
                LocalDate.of(2019, Month.APRIL, 16));
        System.out.println("Period between now and April 16th:");
        System.out.println(period);

        // Duration defines distance on times
        Duration duration = Duration.ofSeconds(30);
        Duration restofDay = Duration.between(LocalTime.now(),
                LocalTime.MIDNIGHT);    // Actually goes backwards to
        // midnight
        duration.get(ChronoUnit.SECONDS);

        System.out.println("Now: " + LocalTime.now());
        System.out.println("30 seconds: " + LocalTime.now().plus(duration));
        System.out.println("To end of today: " + LocalTime.now().plus(restofDay));
        System.out.println("duration variable: " + duration.get(ChronoUnit.SECONDS));
        System.out.println("Restofday: " + restofDay.get(ChronoUnit.SECONDS));


    }
}
