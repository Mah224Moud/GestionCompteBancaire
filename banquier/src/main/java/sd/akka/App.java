package sd.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.util.Scanner;
import sd.akka.actor.*;
import sd.akka.database.DatabaseManager;
import sd.akka.models.CommonModel;
import sd.akka.utils.Login;
import sd.akka.utils.Utils;
import sd.akka.utils.CreateCustomer;
import sd.akka.utils.Message;
import sd.akka.utils.Balance;

import java.util.Scanner;
public class App {
    private CommonModel commonModel;
    private DatabaseManager databaseManager;

    public App() {
        databaseManager = new DatabaseManager();
        commonModel = new CommonModel(databaseManager);
    }

    public void start(ActorRef bankerActor, ActorSystem actorSystem) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\n\nEntrez votre email : ");
            String email = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String password = scanner.nextLine();

            if (!commonModel.isLogged(email, password, "banker")) {
                System.out.println("Email ou mot de passe incorrect");
            } else {
                bankerActor.tell(new Login(email, password), ActorRef.noSender());

                while (true) {
                    Utils.menuBanker();
                    System.out.print("Votre choix : ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("Votre choix: Voir le solde d'un client");
                            System.out.print("\n\nEntrez le numéro de compte : ");
                            int accountNumber = scanner.nextInt();
                            scanner.nextLine();
                            if(!commonModel.accountExists(accountNumber)) {
                                System.out.println("Le numéro de compte est invalide!!!\n");
                                break;
                            }
                            bankerActor.tell(new Balance(accountNumber, "Voir le solde"), ActorRef.noSender());
                            break;
                        case 2:
                            System.out.println("Votre choix: Consutler la liste des clients connecté");
                            break;
                        case 3:
                            System.out.println("Votre choix: Ajouter un client");
                            System.out.println("\n\nEntrez les informations du client: ");
                            String name = Utils.getCustomerName();
                            String firstname = Utils.getCustomerFirstname();
                            String gender = Utils.getCustomerGender();
                            String address = Utils.getCustomerAddress();
                            String phone = Utils.getCustomerPhone();
                            String emailCustomer = Utils.getCustomerEmail();
                            String passwordCustmer = Utils.getCustomerPassword();
                            String type = Utils.getCustomerType();
                            CreateCustomer createCustomer = new CreateCustomer(
                                    0,
                                    name,
                                    firstname,
                                    gender,
                                    address,
                                    phone,
                                    type,
                                    0,
                                    0,
                                    emailCustomer,
                                    passwordCustmer
                            );

                            bankerActor.tell(createCustomer, ActorRef.noSender());
                            break;
                        case 4:
                            System.out.println("Votre choix: Quitter");
                            endSystem(actorSystem);
                            return;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }

                }
            }
            
        }
    }

    public void endSystem(ActorSystem actorSystem) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Banker");
        ActorRef banker = actorSystem.actorOf(BankerActor.props(actorSystem), "banker");

        App app = new App();
        app.start(banker, actorSystem);

        app.endSystem(actorSystem);
    }
}
