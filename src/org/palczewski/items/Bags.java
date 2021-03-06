/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.items;


import java.util.*;

/*
Main class to handle inventory. The general methods will be defined here
 and inherited by the sub-classes.
 */
public class Bags {

    private Map<String, Integer> set = new HashMap<>(10);

    Bags(String s, int i) {

        // Initialize set with an item
        set.put(s, i);
        if(set.containsKey(s)) {
            System.out.println(i + " " + s + " added to " + this);
        } else {
            System.out.println("Problem adding " + s + " to " + this);
        }
    }

    public final void addTo(String s, int i) {
        if(set.containsKey(s)) {
            addQty(s, i);
        } else {
            set.put(s, i);
            System.out.println(i + " " + s + " added to " + this);
        }
    }

    public final void removeFrom(String s) {
        // Test to see if contains
        if(set.containsKey(s)) {
            if(set.containsValue(0)) {
                set.remove(s);
                System.out.println(s + " removed from " + this);
            } else {
                System.out.println("Cannot remove " + s);
            }

        } else {
            System.out.println(s + " cannot be found!");
        }


    }

    public final void showItems() {
        int i = 1;
        System.out.println("Contents of " + this);
        for (Map.Entry<String, Integer> stringIntegerEntry : set.entrySet()) {
            System.out.println(i + ":\t" + stringIntegerEntry.getKey() + ": " + stringIntegerEntry.getValue());
            i++;
        }
    }

    private void addQty(String s, int i) {
        // Check if we have that item
        if(set.containsKey(s)) {
            int tmp = set.get(s);
            set.replace(s, (tmp + i));
            System.out.println("Added " + i + " " + s);
        } else {
            System.out.println(s + " cannot be found!");
        }
    }

    public final void removeQty(String s, int i) {
        if(set.containsKey(s)) {
            if(i <= (set.get(s))) {
                int tmp = set.get(s);
                set.replace(s, (tmp - i));
                System.out.println("Removed " + i + " from " + s);
            } else {
                System.out.println("Too many quantities to remove, try " +
                        "again.");
            }
        } else {
            System.out.println(s + " cannot be found");
        }
    }


    @Override
    public String toString() {
        return "Things{" +
                "set=" + set +
                '}';
    }

    public final Map<String, Integer> getSet() {
        return set;
    }

    public final void setSet(Map<String, Integer> set) {
        this.set = set;
    }
}



