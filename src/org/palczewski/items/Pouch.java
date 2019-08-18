/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;


public class Pouch extends Bags {

    private static final String NAME = "pouch";

    // Create Pouch collection
    public Pouch(String s, int i) {
        /*
        Calls parent class and uses its hashSet object.
         */
        super(s, i);

    }

    public final String toString() {
        return NAME;
    }
}
