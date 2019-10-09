/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Simple class that contains both Pouch and Bookbag objects to attach to
Player
 */
public class Inventory {

    private final Map<Pouch, Bookbag> inventory =
            new LinkedHashMap<>(10);

    public Inventory(Pouch pouch, Bookbag bag) {

        inventory.put(pouch, bag);
    }

    private void showInventory() {

        //
    }

}
