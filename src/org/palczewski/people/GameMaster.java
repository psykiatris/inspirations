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
        setStamina(100000.0);
        setHealth(100000.0);

    }

    /*
    in the off-chance a player decides to fight the GameMaster...
     */
    public void resurrect() {
        if(getStamina() <= 500) {
            setStamina(100000.0);
        } else if(getHealth() <= 500) {
            setHealth(100000.0);
        }

        }

        /*
    // Got to be benevolent
    public void resurrectPlayer(Player name) {
        // This would update MY health
        // Need to get name of player to resurrect
        setHealth(50.0);
    }

         */

    // Create stuff
    public void create() {
        // Empty for now...
    }
}
