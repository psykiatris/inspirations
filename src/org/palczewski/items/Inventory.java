/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

import java.io.LineNumberReader;
import java.util.*;

/*
Simple class that contains both Pouch and Bookbag objects to attach to
Player
 */
public class Inventory {

    private final List<String> inventory =
            new LinkedList<>();

    public Inventory(Pouch pouch, Bookbag bag) {
        /*
        Get the keys to store item names in inventory
         */
        Map<String, Integer> tmpPouch = pouch.getSet();
        Map<String, Integer> tmpBag = bag.getSet();
        inventory.addAll(tmpPouch.keySet());
        inventory.addAll(tmpBag.keySet());


    }

    // Update inventory
    public final List<String> updateInventory(String name) {
        int tmp = inventory.size();
        if(!inventory.contains(name))
            inventory.add(tmp -1, name);
        // Maintain list order
        Collections.sort(inventory);

        return Collections.unmodifiableList(inventory);
    }

    public final String toString() {
        return String.format("\tInventory: %s", inventory);
    }


}
