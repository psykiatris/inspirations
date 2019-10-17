/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.people;

import org.palczewski.items.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
This will be a basic player class.
 */
public class Player {

    // Basic to players
    private final String name;
    private final Bookbag bag;
    private final Pouch pouch;
    private double health;
    private double stamina;

    // Object that contains Player and inventory
    private static final Map<Player, Inventory> player =
            new LinkedHashMap<>(10);
    private final Inventory inventory;

    Player(String name) {

        // Sets name and assigns stats
        this.name = name;
        stamina = 100;
        health = 100;
        // Initialzise inventory
        bag = new Bookbag(NameConstants.BIBLE, 1);
        pouch = new Pouch(NameConstants.APPLE, 1);
        // Store items in inventory
        inventory = new Inventory(pouch, bag);
        player.put(this, inventory);

    }

    public void listPlayers() {
        Set<Map.Entry<Player, Inventory>> set = player.entrySet();
        for (Map.Entry<Player, Inventory> value : set) {
            System.out.println(value.getKey());
            System.out.println(value.getValue());
        }
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

    public final void updatePlayer(Player player) {

        /*
        Saves to Player object when info changes (stamina, health,
        // inventory)
         */

    }

    public final void addToBag(String name, int qty) {
        bag.addTo(name, qty);
        inventory.addInventory(name);

    }

    public final void removeFromBag(String name) {
        /*
        calls bags remove
         */
        bag.removeFrom(name);
        Map<String, Integer> tmp = bag.getSet();
        // Checks main map
        // to ensure item really deleted.
        if(!tmp.containsKey(name))
            inventory.removeInventory(name);

    }

    public final void addToPouch(String name, int qty) {
        /*
        Calls pouch remove
         */
        pouch.addTo(name, qty);
        inventory.addInventory(name);
    }

    public final void removeFromPouch(String name) {
        /*
        Uses removeFrom()
         */
        pouch.removeFrom(name);
        Map<String, Integer> tmp = pouch.getSet();
        if(!tmp.containsKey(name))
            inventory.removeInventory(name);


    }



    @Override
    public final String toString() {
        return String.format("Player: %s\n\tStamina: %.0f\n\tHealth: %.0f",
                name, stamina, health);
    }
}
