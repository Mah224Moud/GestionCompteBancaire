package com.gestion.compte.bancaire.actors;

import java.util.ArrayList;
import com.gestion.compte.bancaire.utils.Transaction;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class BankActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Transaction.class, transaction -> {
                    String message = transaction.getMessage();
                    switch (message) {
                        case "Faire un depot":
                            log.info(
                                    "\nMessage recu par la banque: {} \nMontant recu par la banque: {}",
                                    message,
                                    transaction.getAmount());
                            break;
                        case "Faire un retrait":
                            log.info(
                                    "\nMessage recu par la banque: {} \nMontant recu par la banque: {}",
                                    message,
                                    transaction.getAmount());
                            break;
                        default:
                            log.info("Message non traité par la banque");
                            break;
                    }
                })
                .match(String.class, s -> {
                    log.info("message recu par la banque: {}", s);
                })
                .matchAny(o -> log.info("Received unknown message"))
                .build();
    }
}
