package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import sd.akka.utils.Transaction;
import sd.akka.utils.Message;
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
                .matchAny(message -> System.out.println(message))
                .build();
    }

    public static Props props(ActorSystem actorSystem) {
        return Props.create(BankActor.class, () -> new BankActor(actorSystem));
    }
}
