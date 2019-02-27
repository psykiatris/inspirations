/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski;

import org.palczewski.items.Bookbag;
import org.palczewski.items.NameConstants;
import org.palczewski.items.Pouch;
import org.palczewski.people.GameMaster;

public class PeopleTest {

    public static void main(String[] args) {

        GameMaster p = new GameMaster("Patrick");


        Pouch myFood = new Pouch(NameConstants.APPLE, 1);
        Bookbag myBag = new Bookbag(NameConstants.BIBLE, 1);


        myBag.addTo(NameConstants.TRACT, 100);
        myBag.showItems();

        // Try to add duplicate
        myBag.addTo(NameConstants.BIBLE, 2);
        myBag.showItems();











    }
}
