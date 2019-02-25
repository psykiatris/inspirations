/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.people;
/*
This definies game adamin duties. Anyone assigned to this class is
invincible.
 */
public class GameMaster extends Player {

    public GameMaster(String name) {

        super(name);
        stamina = 100000.0;
        health = 100000.0;

    }

    /*
    in the off-chance a player decides to fight the GameMaster...
     */
    public void resurrect() {
        if(stamina <= 500) {
            stamina = 100000.0;
        } else (health <= 500) {
            health = 100000.0;
        }

        }

    // Got to be benevolent
    public void resurrectPlayer(Player name) {
        health = 50.0;
    }

    // Create stuff
    public void create() {
        // Empty for now...
    }
}
