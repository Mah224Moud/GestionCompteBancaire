package com.gestion.compte.bancaire;

import com.gestion.compte.bancaire.actors.BankActorSystem;
import com.gestion.compte.bancaire.models.CommonModel;
import com.gestion.compte.bancaire.utils.Login;

import akka.actor.ActorRef;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BankActorSystem bankActorSystem = new BankActorSystem();
    }
}