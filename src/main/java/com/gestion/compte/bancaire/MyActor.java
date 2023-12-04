package com.gestion.compte.bancaire;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class MyActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, message -> {
                    System.out.println("Received message: " + message);
                })
                .build();
    }
}
