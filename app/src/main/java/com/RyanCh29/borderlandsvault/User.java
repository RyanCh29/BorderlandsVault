package com.RyanCh29.borderlandsvault;

import com.RyanCh29.borderlandsvault.Builds.Build;

/*
 * User Class
 * This class is for holding all the data for the user including name, inventory, builds, preferences, etc.
 *
 */
public class User {
    private String name;
    private Inventory inventory;
    private Build[] builds;

    public User(Inventory inv, Build[] b) {
        this.inventory = inv;
        this.builds = b;

    }

    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public void setBuilds(Build[] builds) { this.builds = builds; }

    public Inventory getInventory() { return inventory; }
    public Build[] getBuilds() { return builds; }
}
