package sd.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.util.Scanner;
import sd.akka.actor.*;
import sd.akka.database.DatabaseManager;
import sd.akka.models.CommonModel;
import sd.akka.utils.Login;
import sd.akka.utils.Utils;
import sd.akka.utils.Message;

public class App {
    private CommonModel commonModel;
    private DatabaseManager databaseManager;

    public App() {
        databaseManager = new DatabaseManager();
        commonModel = new CommonModel(databaseManager);
    }

    /**
     * Starts the function with the given customer actor and actor system.
     *
     * @param customerActor the customer actor
     * @param actorSystem   the actor system
     */
    public void start(ActorRef customerActor, ActorSystem actorSystem) {
        try (Scanner scanner = new Scanner(System.in)) {
            String email = "";
            String password = "";
            boolean isLogged = false;

            do {
                try {
                    System.out.print("\n\nEntrez votre email : ");
                    email = scanner.nextLine();
                    System.out.print("Entrez votre mot de passe : ");
                    password = scanner.nextLine();

                    isLogged = commonModel.isLogged(email, password, "customer");

                    if (!isLogged) {
                        System.out.println("Email ou mot de passe incorrect");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    isLogged = false;
                }
            } while (!isLogged);
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
                        System.out.println("Option choisie: Consutler l'historique des transactions");
                        customerActor.tell(new Message("Consutler l'historique des transactions", 0.0),
                                ActorRef.noSender());
                        break;
                    case "5":
                        System.out.println("Au revoir !");
                        endSystem(actorSystem);
                        return;
                    default:
                        System.out.println("Choix invalide");
                        break;
                }
            }

        }
    }

    /**
     * Ends the given actor system.
     *
     * @param actorSystem the actor system to be terminated
     * @return void
     */
    public void endSystem(ActorSystem actorSystem) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
    }

    /**
     * Initializes the main function of the Java program.
     *
     * @param args the command line arguments passed to the program
     */
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Customer");
        ActorRef customer = actorSystem.actorOf(CustomerActor.props(actorSystem), "customer");

        App app = new App();
        app.start(customer, actorSystem);

        app.endSystem(actorSystem);
    }
}
