package com.gestion.compte.bancaire;

import com.gestion.compte.bancaire.actors.BankActorSystem;
import com.gestion.compte.bancaire.utils.Login;

import akka.actor.ActorRef;

public class App {
    public static void main(String[] args) {
        BankActorSystem bankActorSystem = new BankActorSystem();

        Login login = new Login("banquier@test.com", "banquier");
        bankActorSystem.getBankerActor().tell(login, ActorRef.noSender());
    }
}