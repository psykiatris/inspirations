/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski;

import org.palczewski.people.GameMaster;
import org.palczewski.people.Mortal;


public class PeopleTest {


    public static void main(String[] args) {

        GameMaster p = new GameMaster("Patrick");
        p.listPlayers();
        Mortal g = new Mortal("Gloria");
        g.addToBag("Tract", 100);
        g.listPlayers();
        g.removeFromPouch("Apple");
        g.listPlayers();











    }
}
