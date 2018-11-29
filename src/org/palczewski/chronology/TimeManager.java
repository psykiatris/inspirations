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
        Chronology chrono = IsoChronology.INSTANCE;
        LocalDate myDate = LocalDate.of(2017, 1, 1);
        ChronoLocalDateTime<?> cdt =
                chrono.date(myDate).atTime(LocalTime.NOON);
        System.out.println(cdt);
    }
}
