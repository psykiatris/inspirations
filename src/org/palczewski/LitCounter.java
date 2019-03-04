/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski;

import java.util.*;

/*
This is a class for the global literature counter. It will be available
to all players and will be static.

Will have its own methods and is instantiated by the Bookbag class upon
creation so that it is accessible.
 */
public class LitCounter {

    Map<String, Integer> lit = new HashMap<>(10);


    /*
    This "stocks" the lit counter. This can be run manually by the game
    master or be assigned a TimerTask thread to run at intervals. If so,
     it will be made private or protected.
     */
    public void stockLit() {
        if(lit.isEmpty()) {
            lit.put("Bible", 100);
            lit.put("WT", 100);
            lit.put("Awake!", 100);
            lit.put("Tract", 100);
            lit.put("Teach", 100);

            } else {
            Set<String> litSet = lit.keySet();
            for(String h : litSet) {
                lit.replace(h, 100);
            }

        }
        System.out.println("Lit Counter stocked successfully");
    }



    public void listLit() {
        /*
        This displays list of lit counter.
         */
        int i = 1;
        System.out.println("Contents of " + this);
        for (Map.Entry<String, Integer> stringIntegerEntry :
                lit.entrySet()) {
            System.out.println(i + ":\t" + stringIntegerEntry.getKey() + ": " + stringIntegerEntry.getValue());
            i++;
        }

    }

    public String toString() {
        return "Literature Counter";
    }

    /*
    This is called by the Bookbag instance. It "virutlly" moves the
    item, so that it appears to decrease the quantity here and increase
    bag quantities there. This works until I learn how to move the
    actual object from one to the other.
     */
    public int takeFrom(String s, int i) {
        if(lit.containsKey(s)) {
            int tmp = lit.get(s);
            lit.replace(s, (tmp - i));
            return i;
        } else if ((lit.get(s)) == 0) {
            System.out.println("No more " + s + " left.");
            return 0;
        }
        return 0;
    }


}
