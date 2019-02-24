/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.people;
/*
This will be a basic player class.
 */
public class Player {

    // Basic to players
    String name;
    double health;
    double stamina;

    Player(String name) {

        this.name = name;
        stamina = 100.0;
        health = 100;

    }

    Player() {}

    public void getInfo() {

        System.out.println("Player Info:");
        System.out.println("\tName: " _ name);
        System.out.println("\tStamina: " + stamina);
        System.out.println("\tHaalth: " + health);
    }
}
