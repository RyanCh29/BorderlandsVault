package com.RyanCh29.borderlandsvault.Gear;

public class Weapon extends Gear {
    private int damage;
    private int multiplier;
    private int accuracy;
    private int handling;
    private double reload;
    private double fireRate;
    private int magazineSize;
    private String[] bonuses;
    private Anoinment anoint;

    //Constructor without anointment
    public Weapon(String n, int lvl, int ml, int sc, int dmg, int multi, int acc, int hand, double r, double fr, int mag, String[] b) {
        super(n, lvl, ml, sc);
        this.damage = dmg;
        this.multiplier = multi;
        this.accuracy = acc;
        this.handling = hand;
        this.reload = r;
        this.fireRate = fr;
        this.magazineSize = mag;
        this.bonuses = b;
    }

    //Constructor with annointment
    public Weapon(String n, int lvl, int ml, int sc, int dmg, int multi, int acc, int hand, double r, double fr, int mag, String[] b, int a) {
        super(n, lvl, ml, sc);
        this.damage = dmg;
        this.multiplier = multi;
        this.accuracy = acc;
        this.handling = hand;
        this.reload = r;
        this.fireRate = fr;
        this.magazineSize = mag;
        this.bonuses = b;
        anoint.setAnoint(a); // set anointment based on ID
    }
}
