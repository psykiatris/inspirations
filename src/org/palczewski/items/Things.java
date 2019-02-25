/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;


import java.util.HashSet;
import java.util.Set;

/*
Main class to handle inventory. The general methods will be defined here
 and inherited by the sub-classes.
 */
public class Things {

    Set<String> set = new HashSet<>(10);

    public Things(String s) {

        // Initialize set with an item
        set.add(s);
        if(set.contains(s)) {
            System.out.println(s + " has been added.");
        } else {
            System.out.println("Problem adding " + s);
        }
    }

    public void addTo(String s) {
        set.add(s);
    }

    public void removeFrom(String s) {
        // Test to see if contains
        if(set.contains(s)) {
            set.remove(s);
        } else {
            System.out.println(s + " is not found!");
            }


        }

    public void showItems() {
        for (String h : set) {
            System.out.println(h);
        }
    }

    @Override
    public String toString() {
        return "Things{" +
                "set=" + set +
                '}';
    }
}



