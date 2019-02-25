/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski;

import org.palczewski.items.Bookbag;
import org.palczewski.items.Pouch;
import org.palczewski.people.GameMaster;

public class PeopleTest {

    public static void main(String[] args) {

        GameMaster p = new GameMaster("Patrick");

        p.getInfo();

        Pouch myFood = new Pouch("Apple");
        Bookbag myBag = new Bookbag("Bible");


        myBag.addTo("Tract");
        myBag.showItems();
        // Remove
        myBag.removeFrom("Bible");
        myBag.showItems();










    }
}
