package com.gestion.compte.bancaire;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;

import com.gestion.compte.bancaire.utils.Utils;
import com.gestion.compte.bancaire.actors.BankActorSystem;
import com.gestion.compte.bancaire.actors.CustomerActor;
import com.gestion.compte.bancaire.models.CommonModel;
import com.gestion.compte.bancaire.utils.Login;
import com.gestion.compte.bancaire.utils.Message;

import java.util.Scanner;

public class CustomerApp {
    private final ActorRef customerActor;
    private CommonModel commonModel = new CommonModel();

    public CustomerApp(BankActorSystem bankActorSystem) {
        ActorSelection bankActor = bankActorSystem.getSystem()
                .actorSelection("akka://BankActorSystem@127.0.0.1:255/user/bankActor");
        this.customerActor = bankActorSystem.getSystem().actorOf(Props.create(CustomerActor.class, bankActor),
                "customerActor");
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez votre email : ");
            String email = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String password = scanner.nextLine();

            if (!commonModel.isLogged(email, password, "customer")) {
                System.out.println("Email ou mot de passe incorrect");
            } else {
                customerActor.tell(new Login(email, password), ActorRef.noSender());
                while (true) {
                    Utils.menuCustomer();

                    System.out.print("Entrez votre choix: ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.println("Option choisie: Voir le solde");
                            customerActor.tell(new Message("Voir le solde", 0.0), ActorRef.noSender());
                            break;
                        case "2":
                            System.out.println("Option choisie: Faire un depot");
                            System.out.print("Entrez le montant à deposer : ");
                            double deposit = scanner.nextDouble();
                            customerActor.tell(new Message("Faire un depot", deposit), ActorRef.noSender());
                            break;
                        case "3":
                            System.out.println("Option choisie: Faire un retrait");
                            System.out.print("Entrez le montant à retirer : ");
                            double withdraw = scanner.nextDouble();
                            customerActor.tell(new Message("Faire un retrait", withdraw), ActorRef.noSender());
                            break;
                        case "4":
                            System.out.println("Au revoir !");
                            return;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BankActorSystem bankActorSystem = new BankActorSystem();
        CustomerApp customerApp = new CustomerApp(bankActorSystem);
        customerApp.start();
    }

}