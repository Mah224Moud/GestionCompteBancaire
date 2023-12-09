package com.gestion.compte.bancaire;

import com.gestion.compte.bancaire.Actors.BankActorSystem;
import com.gestion.compte.bancaire.models.CommonModel;
import akka.actor.ActorRef;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        BankActorSystem bankActorSystem = new BankActorSystem();
        CommonModel commonModel = new CommonModel();

        ActorRef customerActor = bankActorSystem.getCustomerActor();
        ActorRef bankerActor = bankActorSystem.getBankerActor();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        if (commonModel.isLogged(email, password, "customer")) {
            customerActor.tell(new Login(email, password), ActorRef.noSender());
            while (true) {
                menu();
                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        customerActor.tell("Voir le solde", ActorRef.noSender());
                        break;
                    case "2":
                        System.out.print("Entrez le montant du dépôt : ");
                        String amount = scanner.nextLine();
                        customerActor.tell("Faire un dépôt : " + amount, ActorRef.noSender());
                        break;
                    case "3":
                        System.out.print("Entrez le montant du retrait : ");
                        amount = scanner.nextLine();
                        customerActor.tell("Faire un retrait : " + amount, ActorRef.noSender());
                        break;
                    case "4":
                        bankActorSystem.shutdown();
                        return;
                    default:
                        System.out.println("Option non reconnue");
                }
            }
        } else {
            System.out.println("Email ou mot de passe incorrect");
        }
    }

    public static void menu() {
        System.out.println(
                "\n" +
                        "*********************************************\n" +
                        "******************** MENU *******************\n" +
                        "*********************************************");
        System.out.println("1. Voir le solde");
        System.out.println("2. Faire un depot");
        System.out.println("3. Faire un retrait");
        System.out.println("4. Quitter");
        System.out.println(
                "*********************************************\n" +
                        "*********************************************\n");
    }
}