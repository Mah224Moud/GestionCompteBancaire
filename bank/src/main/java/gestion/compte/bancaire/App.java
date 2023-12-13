package gestion.compte.bancaire;

import gestion.compte.bancaire.actor.BankActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("bankActorSystem");
        ActorRef bankActor = actorSystem.actorOf(BankActor.props(actorSystem), "bankActor");

        bankActor.tell("Le systeme est pret !!!\nPensez a lancer le banquier puis le client :)", ActorRef.noSender());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
    }
}
