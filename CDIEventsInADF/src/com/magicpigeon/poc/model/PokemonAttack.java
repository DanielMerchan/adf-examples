package com.magicpigeon.poc.model;

/**
 * Encapsulates a Pokemon Attack
 * @author Daniel Merchan Garcia
 */
public class PokemonAttack {
    
    public enum AttackType {
        LIGHTNING("Lightning"), 
        ROCK("Rock"), 
        WATER("Water"),
        PHYSICAL("Phisical"),
        NATURE("Nature");
        
        private final String attackType;
        
        AttackType (String at) {
            this.attackType = at;
        }
       
        public boolean equalsName(String attackType) {
                return attackType.equals(this.attackType);
        }
        
        public String toString() {
               return this.attackType;
            }
        
        public String getString() {
            return this.attackType;
        }
    }
    
    private String attackName;
    
    private AttackType attackType;

    public PokemonAttack(String attackName, PokemonAttack.AttackType attackType) {
        this.attackName = attackName;
        this.attackType = attackType;
    }


    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackType(PokemonAttack.AttackType attackType) {
        this.attackType = attackType;
    }

    public PokemonAttack.AttackType getAttackType() {
        return attackType;
    }
}
