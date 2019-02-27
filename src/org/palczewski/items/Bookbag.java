/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

public class Bookbag extends Things {

    public static final String name = "bookbag";

    public Bookbag(String s, int i) {
        // Initialize bookbag
        super(s, i);
    }

    public String toString() {
        return name;
    }
}
