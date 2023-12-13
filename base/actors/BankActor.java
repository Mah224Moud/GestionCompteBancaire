package com.gestion.compte.bancaire.actors;

import com.gestion.compte.bancaire.utils.Message;
import com.gestion.compte.bancaire.utils.Transaction;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class BankActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef bankerActor;
    private ActorRef customerActor;

    public BankActor(ActorRef customerActor, ActorRef bankerActor) {
        this.customerActor = customerActor;
        this.bankerActor = bankerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Transaction.class, transaction -> {
                    String message = transaction.getMessage();
                    log.info(
                            "\nMessage recu par la banque: {} \nMontant recu par la banque: {}",
                            message,
                            transaction.getAmount());
                    bankerActor.tell(
                            new Transaction(
                                    message,
                                    transaction.getAmount(),
                                    transaction.getAccountNumber()),
                            ActorRef.noSender());
                })
                .match(Message.class, message -> {
                    log.info("\nMessage recu par la banque: {}", message.getMessage());

                    customerActor.tell(
                            message,
                            ActorRef.noSender());
                })
                .match(String.class, s -> {
                    log.info("message recu par la banque: {}", s);
                })
                .matchAny(o -> log.info("Received unknown message"))
                .build();
    }
}
