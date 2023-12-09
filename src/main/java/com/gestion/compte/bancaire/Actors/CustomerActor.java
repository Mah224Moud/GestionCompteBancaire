package com.gestion.compte.bancaire.Actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.gestion.compte.bancaire.Login;
import com.gestion.compte.bancaire.customers.Customer;

public class CustomerActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private Customer customer;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                    switch (s) {
                        case "Voir le solde":
                            customer.seeBalance();
                            break;
                        case "Faire un depot":
                            getSender().tell("Depot effectue", getSelf());
                            break;
                    }
                })
                .match(Customer.class, customer -> {
                    log.info("Received Customer message: {}", customer);
                })
                .match(Login.class, login -> {
                    customer = new Customer(login.getEmail(), login.getPassword());
                    System.out.println(customer.toString());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}