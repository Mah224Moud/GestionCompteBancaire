package com.gestion.compte.bancaire.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.gestion.compte.bancaire.utils.Transaction;
import akka.actor.Props;
import akka.actor.ActorRef;
import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.utils.Login;
import com.gestion.compte.bancaire.utils.Message;

public class BankerActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef bankApp;
    private Banker banker;

    public BankerActor(ActorRef bankApp) {
        this.bankApp = bankApp;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Transaction.class, transaction -> {
                    log.info("Message recu par la banque: {}", transaction.getMessage());
                    String message = transaction.getMessage();
                    switch (message) {
                        case "Faire un depot":
                            String depositMsg = banker.deposit(transaction.getAmount(), transaction.getAccountNumber());
                            bankApp.tell(new Message(depositMsg, 0), ActorRef.noSender());
                            break;
                        case "Faire un retrait":
                            bankApp.tell(transaction, ActorRef.noSender());
                            String withdrawalMsg = banker.withdraw(transaction.getAmount(),
                                    transaction.getAccountNumber());
                            bankApp.tell(new Message(withdrawalMsg, 0), ActorRef.noSender());
                            break;
                        default:
                            log.info("Message non traité par le client");
                            return;
                    }
                    bankApp.tell(transaction, ActorRef.noSender());
                })
                .match(Login.class, login -> {
                    banker = new Banker(login.getEmail(), login.getPassword());
                    System.out.println(banker.toString());
                })
                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}