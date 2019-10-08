/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski;

import org.palczewski.items.Bookbag;
import org.palczewski.items.Inventory;
import org.palczewski.items.NameConstants;
import org.palczewski.items.Pouch;
import org.palczewski.people.GameMaster;
import org.palczewski.people.Player;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class PeopleTest {

    private static final Map<Player, Inventory> player =
            new LinkedHashMap<>(10);

    public static void main(String[] args) {

        GameMaster p = new GameMaster("Patrick");


        Pouch myFood = new Pouch(NameConstants.APPLE, 1);
        Bookbag myBag = new Bookbag(NameConstants.BIBLE, 1);
        Inventory stuff = new Inventory(myFood, myBag);




        player.put(p, stuff);

        // Traverse set
        Set<Player> set = player.keySet();
        for (Player value : set) {
            value.getInfo();

        }









    }
}
