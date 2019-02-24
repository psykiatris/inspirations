/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.people;
/*
This definies game adamin duties. Anyone assigned to this class is
invincible.
 */
public class GameMaster extends Player {

    GameMaster(String name) {

        this.name = name;
        stamina = 100000.0;
        health = 100000.0;


    }
}
