package com.RyanCh29.borderlandsvault;

import com.RyanCh29.borderlandsvault.Gear.Gear;

/*
 * Vault Class
 * This class is responsible for holding and manipulating all the items a User enters into their vault.
 *
 *
 */
public class Vault {
    private Gear[] gear;

    public Vault(Gear[] g) {
        this.gear = g;
    }
}
