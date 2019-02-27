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
        // Remove qty
        System.out.println();
        myBag.addQty("Bible", 4);
        myBag.showItems();

        System.out.println();
        // Now try to remove qty
        myBag.removeQty(NameConstants.BIBLE, 4);

        myBag.showItems();











    }
}
