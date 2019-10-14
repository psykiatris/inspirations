/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

import org.palczewski.LitCounter;

public class Bookbag extends Bags {

    private static final String NAME = "bookbag";
    // Create instance of LitCounter
    private LitCounter lc;



    public Bookbag(String s, int i) {
        // Initialize bookbag
        super(s, i);

    }

    public void getLit(String s, int i) {
        // Get from lit
        int res = lc.takeFrom(s, i);
        addTo(s, res);
        lc.listLit();


    }

    /*
    Overrides the toString method in the superclass to report name of
    this class.
     */
    public final String toString() {
        return NAME;
    }



}
