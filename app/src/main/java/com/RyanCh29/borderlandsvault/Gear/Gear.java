package com.RyanCh29.borderlandsvault.Gear;

public abstract class Gear {
    private String name;
    private int level;
    private int mayhemLevel;
    private int score;

    public Gear(String n, int lvl, int ml, int sc) {
        this.name = n;
        this.level = lvl;
        this.mayhemLevel = ml;
        this.score = sc;
    }

    public void setName(String n) { this.name = n; }
    public void setLevel(int lvl) { this.level = lvl; }
    public void setMayhemLevel(int ml) { this.mayhemLevel = ml; }
    public void setScore(int sc) { this.score = sc; }

    public String getName() { return this.name; }
    public int getLevel() { return this.level; }
    public int getMayhemLevel() { return this.mayhemLevel; }
    public int getScore() { return this.score; }

}
