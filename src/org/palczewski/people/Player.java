/*
 * Copyright (c) 2019.  Author: Patrick Palczewski <psykiatris@gmail.com>. Licensed under GPL 3. See LICENSE for details.
 */

package org.palczewski.people;

import org.palczewski.items.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
A basic Player class. Provides supertype for subclasses with easy access
 to its methods.
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

    /*
    Cannot be instantiated outside its package. Built-in subclasses are
    used to provide public access. Sublass the subclasses to define your
     own player types.
     */
    Player(String name) {

        // Sets name and assigns basic stats
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

    public final void listPlayers() {
        Set<Map.Entry<Player, Inventory>> set = player.entrySet();
        for (Map.Entry<Player, Inventory> value : set) {
            System.out.println(value.getKey());
            System.out.println(value.getValue());
        }
    }


    public final void getInfo() {
        // Get info about a player
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
        // When a player eats or drinks
        health += i;

    }

    public final void loseHealth(double i) {
        // When a player is active
        health -= i;
    }

    public final void gainStamina(double i) {
        // When a player rests for a time
        stamina += i;
    }

    public final void loseStamina(double i) {
        // When a player is busy
        stamina -= i;
    }

    public final void updatePlayer(Player player) {

        /*
        Saves to Player object when info changes (stamina, health,
        // inventory)
         */
        // Possibly used when saving player for persistence.

    }

    public final void addToBag(String name, int qty) {
        /*
        Calls Bags add method

         */
        bag.addTo(name, qty);
        // Ensure item really added
        Map<String, Integer> tmp = bag.getSet();
        if(tmp.containsKey(name))
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
        Calls pouch add
         */
        pouch.addTo(name, qty);
        Map<String, Integer> tmp = pouch.getSet();
        if(tmp.containsKey(name))
            inventory.addInventory(name);

    }

    public final void removeFromPouch(String name) {
        /*
        Uses removeFrom()
        Will update only if item has the quantity of 0 and is truly
        deleted from the pouch.
         */
        // TODO: 10/16/19 Can further generify if I use "this" keyword? 
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
