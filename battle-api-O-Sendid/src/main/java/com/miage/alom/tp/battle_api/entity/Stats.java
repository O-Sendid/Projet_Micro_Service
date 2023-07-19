package com.miage.alom.tp.battle_api.entity;

public class Stats {
	
	
    private Integer maxHp;
    private Integer attack;
    private Integer defense;    
    private Integer speed;
    private Integer hp;



    public Stats() {
    }

    public Stats(Integer speed, Integer defense, Integer attack, Integer hp) {
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.hp = hp;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }
    
    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }
    
    public void init(int level) {
        double attack=5+(this.getAttack()*((double)level/50));
        double defense=5+(this.getDefense()*((double)level/50));
        double speed=5+(this.getSpeed()*((double)level/50));
        double hp=10+level+(this.getHp()*((double)level/50));
        this.setAttack((int) attack);
        this.setDefense((int)defense);
        this.setSpeed((int)speed);
        this.setHp((int)hp);
        this.setMaxHp((int)hp);
    }
    
    
}
