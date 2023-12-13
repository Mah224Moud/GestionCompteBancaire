package com.gestion.compte.bancaire.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.gestion.compte.bancaire.customer.Customer;
import com.gestion.compte.bancaire.utils.Login;
import com.gestion.compte.bancaire.utils.Message;
import com.gestion.compte.bancaire.utils.Transaction;

public class CustomerActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private Customer customer;
    private ActorRef bankApp;

    public CustomerActor(ActorRef bankApp) {
        this.bankApp = bankApp;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, message -> {
                    log.info("\nMessage recu par le client: {}", message.getMessage());
                    String s = message.getMessage();
                    switch (s) {
                        case "Voir le solde":
                            customer.seeBalance();
                            break;
                        case "Faire un depot":
                            bankApp.tell(
                                    new Transaction(message.getMessage(), message.getAmount(),
                                            customer.getAccountNumber()),
                                    ActorRef.noSender());

                            break;
                        case "Faire un retrait":
                            bankApp.tell(
                                    new Transaction(message.getMessage(), message.getAmount(),
                                            customer.getAccountNumber()),
                                    ActorRef.noSender());
                            break;
                        default:
                            log.info("Message non traité par le client");
                            return;
                    }
                })
                .match(Login.class, login -> {
                    customer = new Customer(login.getEmail(), login.getPassword());
                    System.out.println(customer.toString());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}