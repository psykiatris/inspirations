/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.people;
/*
This will be a basic player class.
 */
public class Player {

    // Basic to players
    private final String name;
    private double health;
    private double stamina;

    Player(String name) {

        // Sets name and assigns stats
        this.name = name;
        stamina = 100.0;
        health = 100;

    }


    public final void getInfo() {

        System.out.println("Player Info:");
        System.out.printf("\tName: %s%n", name);
        System.out.printf("\tStamina: %s%n", stamina);
        System.out.printf("\tHealth: %s%n", health);
    }

    // Getters and setters
    final void setStamina(double stamina) {
        this.stamina = stamina;
    }

    final double getStamina() {
        return stamina;
    }

    final void setHealth(double health) {
        this.health = health;
    }

    final double getHealth() {
        return health;
    }

    public final void gainHealth(double i) {
        health += i;

    }

    public final void loseHealth(double i) {
        health -= i;
    }

    public final void gainStamina(double i) {
        stamina += i;
    }

    public final void loseStamina(double i) {
        stamina -= i;
    }


    @Override
    public final String toString() {
        return String.format("Player: %s\n\tStamina: %.0f\n\tHealth: %.0f",
                name, stamina, health);
    }
}
