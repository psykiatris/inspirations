/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

import org.palczewski.LitCounter;

public class Bookbag extends Things {

    public static final String name = "bookbag";
    LitCounter lc;



    public Bookbag(String s, int i) {
        // Initialize bookbag
        super(s, i);


    }

    public void getLit(String s, int i) {
        // Get from lit
        int res = lc.takeFrom(s, i);
        int tmp = set.get(s);
        set.replace(s, (tmp + res));
        System.out.println("Received " + res + " from literature.");
        lc.listLit();

    }

    public String toString() {
        return name;
    }



}
