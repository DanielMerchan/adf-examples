package com.magicpigeon.poc.view.cdi;

import com.magicpigeon.poc.model.PokemonAttack;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;

import javax.inject.Inject;
import javax.inject.Named;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

/**
 * CDI Managed Bean to interact with the pokemon UI actions
 * @author Daniel Merchan Garcia
 */
@Named("pokemon")
@RequestScoped
public class Pokemon {

    @Inject
    Event<PokemonAttack> pokemonAttack;

//    @Resource
//    ManagedExecutorService threadPool;

    private RichPanelGroupLayout asyncPanelTextComp;
    
    private RichOutputText asyncTextComp;

    public Pokemon() {
        super();
    }

    public void attackSync(String attackName, String attackType) {
        PokemonAttack pa = new PokemonAttack(attackName, PokemonAttack.AttackType.valueOf(attackType));
        // Sync Part
        pokemonAttack.fire(pa);
        attackDone(pa);
    }
    
    public void attackAsync(String attackName, String attackType) {
        PokemonAttack pa = new PokemonAttack(attackName, PokemonAttack.AttackType.valueOf(attackType));

        // Async Part
//        CompletionStage<PokemonAttack> completion = this.pokemonAttack.fireAsync(pa);
//        completion.thenAccept(this::attackDone);
    }

    public void attackDone(PokemonAttack pa) {
        System.out.println("The attack has completed!: " + pa.getAttackName());
        getAsyncTextComp().setValue("The attack has completed!: " + pa.getAttackName());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAsyncPanelTextComp());
    }

    public PokemonAttack.AttackType[] getAttackTypes() {
        return PokemonAttack.AttackType.values();
    }

//    public RichOutputText getAsyncTextComp() {
//        System.out.println(asyncTextComp);
//        if (asyncTextComp != null) {
//            return (RichOutputText) asyncTextComp.getComponent();
//        }
//        return null;
//    }
//
//    public void setAsyncTextComp(RichOutputText asyncTextComp) {
//        this.asyncTextComp = ComponentReference.newUIComponentReference(asyncTextComp);
//    }

    public void setAsyncPanelTextComp(RichPanelGroupLayout asyncPanelTextComp) {
        this.asyncPanelTextComp = asyncPanelTextComp;
    }

    public RichPanelGroupLayout getAsyncPanelTextComp() {
        return asyncPanelTextComp;
    }
    
    public void setAsyncTextComp(RichOutputText asyncTextComp) {
        this.asyncTextComp = asyncTextComp;
    }

    public RichOutputText getAsyncTextComp() {
        return asyncTextComp;
    }

}
