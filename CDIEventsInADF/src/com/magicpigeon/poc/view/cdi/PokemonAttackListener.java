package com.magicpigeon.poc.view.cdi;

import com.magicpigeon.poc.model.PokemonAttack;

import java.util.concurrent.TimeUnit;

import javax.enterprise.event.Observes;

public class PokemonAttackListener {
    
    public PokemonAttackListener() {
        super();
    }
    
    public void onPokemonAttackSync(@Observes PokemonAttack at) {
        onPokemonAttack(at);
    }
    
//    public void onPokemonAttackAsync(@ObservesAsync PokemonAttack at) {
//        onPokemonAttack(at);
//    }
    
    private void onPokemonAttack(PokemonAttack at) {
        long initTime = System.currentTimeMillis();
        System.out.println("A Pokemon Attack has been produced");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        long l = TimeUnit.MILLISECONDS.toSeconds(finalTime-initTime);
        System.out.println("Attack: " + at.getAttackName() + " of type: " + at.getAttackType().toString() + "Time: " + l);
    }
}
