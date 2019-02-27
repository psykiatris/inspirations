/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;


import java.util.*;

/*
Main class to handle inventory. The general methods will be defined here
 and inherited by the sub-classes.
 */
public class Things {

    Map<String, Integer> set = new HashMap<>(10);

    public Things(String s, int i) {

        // Initialize set with an item
        set.put(s, i);
        if(set.containsKey(s)) {
            System.out.println(s + " has been added.");
        } else {
            System.out.println("Problem adding " + s);
        }
    }

    public void addTo(String s, int i) {
        set.put(s, i);
    }

    public void removeFrom(String s) {
        // Test to see if contains
        if(set.containsKey(s)) {
            if(set.containsValue(0)) {
                set.remove(s);
            } else {
                System.out.println("Cannot remove " + s);
            }

        } else {
            System.out.println(s + " cannot be found!");
        }


    }

    public final void showItems() {
        Set<String> newSet = set.keySet();
        int i = 1;
        for (String s : newSet) {
            System.out.println(i + ":\t" + s + ": " + set.get(s));
        }
    }

    @Override
    public String toString() {
        return "Things{" +
                "set=" + set +
                '}';
    }
}



