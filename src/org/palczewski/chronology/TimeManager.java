/*
 * Copyright (c) 2018.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.chronology;

import javax.swing.plaf.basic.BasicMenuUI;
import java.time.*;
import java.time.chrono.*;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/*
* The goal of this class is to utilize the new Date-Time API that was
* releases in JDK8, with the java.time packages. This file will attempt
* to develop a custom-clock for a future multi-player game.
* */
public class TimeManager {


    public static void main(String[] args) {

        /*
        * Simply... The following line creates an instance of the
        * Chronology desired.
        *
        * Then you can call the methods associated with the Chronology
        * */


        IsoChronology chron = IsoChronology.INSTANCE;
        System.out.println(chron.getCalendarType());

        ChronoLocalDate date = chron.date(2020, 9, 1);
        System.out.println(date);

        LocalTime time = LocalTime.of(12, 1, 0);

        // 0 is BC, 1 is CE
        Era era = chron.eraOf(1);
        System.out.println(era);

        Period period = chron.period(1, 12, 31);



    }

}