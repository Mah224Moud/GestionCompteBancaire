package sd.akka.utils;

import java.io.Serializable;
import java.util.List;

public class Histories implements Serializable {
    private List<History> histories;

    public Histories(List<History> histories) {
        this.histories = histories;
    }

    /**
     * Retrieves the list of histories.
     *
     * @return the list of histories
     */
    public List<History> getHistories() {
        return this.histories;
    }

    /**
     * Prints the transaction history of the current object.
     *
     * @return description of return value
     */
    public void seeHistories() {
        System.out.println("\n***********************************************");
        System.out.println("******** HISTORIQUE DES TRANSACTIONS ****1*****");
        System.out.println("***********************************************\n");

        if (this.getHistories().isEmpty()) {
            System.out.println("\nVous n'avez effectuer aucune transaction !!!");
        } else {
            for (History history : this.getHistories()) {
                System.out.println("\n" + history);
            }
        }
    }
}
