package com.gestion.compte.bancaire.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.gestion.compte.bancaire.utils.Transaction;

public class BankerActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Transaction.class, transaction -> {
                    log.info("Received transaction message: {}", transaction.getAccountNumber());
                })
                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}