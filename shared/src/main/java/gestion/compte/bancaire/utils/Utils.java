package gestion.compte.bancaire.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.mindrot.jbcrypt.BCrypt;

public class Utils {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isValidPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,###.00", new DecimalFormatSymbols(Locale.US));
        return df.format(amount) + "€";
    }

    public static void menuCustomer() {
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