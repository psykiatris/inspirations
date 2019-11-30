/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;

import java.util.*;

/*
This class defines a means of including the Pouch and Bookbag object
into a list for display when information about a player is requested. It
 maintains sort [A-Z] order.
 */
public class Inventory {

    private final List<String> inventory =
            new LinkedList<>();

    public Inventory(Pouch pouch, Bookbag bag) {
        /*
        Initializes with getting current Bookbag and Pouch keys.
         */
        Map<String, Integer> tmpPouch = pouch.getSet();
        Map<String, Integer> tmpBag = bag.getSet();
        inventory.addAll(tmpPouch.keySet());
        inventory.addAll(tmpBag.keySet());




    }

    public final List<String> addInventory(String name) {
        /*
        Add items to the inventory list upon
        addition to bookbag or pouch
         */
        int tmp = inventory.size();
        if(!inventory.contains(name))
            inventory.add(tmp -1, name);
        // Maintain list order
        Collections.sort(inventory);

        return Collections.unmodifiableList(inventory);
    }

    public final List<String> removeInventory(String name) {

        // Already tested via Player call
        inventory.remove(name);

        // Maintain list order
        Collections.sort(inventory);

        return Collections.unmodifiableList(inventory);

    }

    public final void getInventory() {
        inventory.forEach(String::toString);
    }

    public final String toString() {
        return String.format("\tInventory: %s", inventory);
    }




}
