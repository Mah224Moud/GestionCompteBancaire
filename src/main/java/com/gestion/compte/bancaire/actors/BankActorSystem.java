package com.gestion.compte.bancaire.actors;

import com.gestion.compte.bancaire.customer.Customer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class BankActorSystem {

    private ActorSystem actorSystem;
    private ActorRef customerActor;
    private ActorRef bankerActor;

    public BankActorSystem() {
        actorSystem = ActorSystem.create("BankActorSystem");

        customerActor = actorSystem.actorOf(Props.create(CustomerActor.class), "clientActor");
        bankerActor = actorSystem.actorOf(Props.create(BankerActor.class), "bankerActor");
    }

    public ActorRef getCustomerActor() {
        return customerActor;
    }

    public ActorRef getBankerActor() {
        return bankerActor;
    }

    public void shutdown() {
        actorSystem.terminate();
    }
}