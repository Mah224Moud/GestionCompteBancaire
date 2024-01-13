package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import sd.akka.utils.Transaction;
import sd.akka.utils.Message;
import sd.akka.utils.HistMessage;
import sd.akka.utils.Histories;
import sd.akka.utils.Logged;

public class BankActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorSelection customer;
    private ActorSelection banker;

    private ActorSystem actorSystem;

    public BankActor(final ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.customer = actorSystem.actorSelection("akka://Customer@127.0.0.1:8011/user/customer");
        this.banker = actorSystem.actorSelection("akka://Banker@127.0.0.1:8065/user/banker");
    }

    /**
     * Creates and returns a Receive object that defines the behavior of the actor.
     *
     * @return A Receive object that defines the behavior of the actor.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Transaction.class, transaction -> {
                    String message = transaction.getMessage();
                    log.info(
                            "\nMessage recu par la banque: {} \nMontant recu par la banque: {}",
                            message,
                            transaction.getAmount());

                    banker.tell(
                            new Transaction(
                                    message,
                                    transaction.getAmount(),
                                    transaction.getAccountNumber()),
                            ActorRef.noSender());
                })
                .match(Message.class, message -> {
                    log.info("\nMessage recu par la banque: {}", message.getMessage());

                    customer.tell(
                            message,
                            ActorRef.noSender());
                })
                .match(Logged.class, logged -> {
                    log.info("\n{}", logged);
                })
                .match(String.class, s -> {
                    log.info("\n{}", s);
                })
                .match(HistMessage.class, histMessage -> {
                    log.info("\nMessage recu par la banque: {}", histMessage.getMessage());
                    banker.tell(
                            new HistMessage(histMessage.getMessage(), histMessage.getAccountNumber()),
                            ActorRef.noSender());
                })
                .match(Histories.class, histories -> {
                    log.info("\nMessage recu par la banque: L'historique des transactions...\n");
                    customer.tell(
                            histories,
                            ActorRef.noSender());
                    log.info("\nL'historique des transactions envoyées au client...\n");
                })
                .matchAny(message -> System.out.println(message))
                .build();
    }

    /**
     * Create the properties for the BankActor.
     *
     * @param actorSystem the actor system to be used by the BankActor
     * @return the properties for the BankActor
     */
    public static Props props(ActorSystem actorSystem) {
        return Props.create(BankActor.class, () -> new BankActor(actorSystem));
    }
}
