/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

import org.palczewski.LitCounter;

public class Bookbag extends Bags {

    private static final String NAME = "bookbag";

    public Bookbag(String s, int i) {
        // Initialize bookbag
        super(s, i);

    }

    /*
    Overrides the toString method in the superclass to report name of
    this class.
     */
    public final String toString() {
       return NAME;
    }




}
