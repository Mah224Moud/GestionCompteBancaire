package com.gestion.compte.bancaire;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        // Créer le système Akka
        ActorSystem system = ActorSystem.create("MyActorSystem");

        // Créer un acteur
        ActorRef myActor = system.actorOf(Props.create(MyActor.class), "myActor");

        // Envoyer un message à l'acteur
        myActor.tell("Hello Akka from Actor", ActorRef.noSender());

        // Arrêter le système Akka
        system.terminate();
    }
}
