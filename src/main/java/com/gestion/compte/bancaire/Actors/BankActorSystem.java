package com.gestion.compte.bancaire.Actors;

import com.gestion.compte.bancaire.customers.Customer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class BankActorSystem {

    private ActorSystem actorSystem;
    private ActorRef customerActor;
    private ActorRef bankerActor;
    // private ActorRef bankActor;

    public BankActorSystem() {
        actorSystem = ActorSystem.create("BankActorSystem");

        customerActor = actorSystem.actorOf(Props.create(CustomerActor.class), "clientActor");
        bankerActor = actorSystem.actorOf(Props.create(BankerActor.class), "bankerActor");
        // bankActor = actorSystem.actorOf(Props.create(BankActor.class), "bankActor");
    }

    public ActorRef getCustomerActor() {
        return customerActor;
    }

    public ActorRef getBankerActor() {
        return bankerActor;
    }

    // public ActorRef getBankActor() {
    // return bankActor;
    // }

    public void shutdown() {
        actorSystem.terminate();
    }
}