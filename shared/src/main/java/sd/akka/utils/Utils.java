package sd.akka.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

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
        System.out.println("4. Consutler l'historique des transactions");
        System.out.println("5. Quitter");
        System.out.println(
                "*********************************************\n" +
                        "*********************************************\n");
    }

    public static void menuBanker() {
        System.out.println(
                "\n" +
                        "*********************************************\n" +
                        "******************** MENU *******************\n" +
                        "*********************************************");
        System.out.println("1. Voir le solde d'un client");
        System.out.println("2. Consutler la liste des clients connecté");
        System.out.println("3. Ajouter un client");
        System.out.println("4. Quitter");
        System.out.println(
                "*********************************************\n" +
                        "*********************************************\n");
    }

    public static String getCustomerName() {
        System.out.print("Entrez le nom du client: ");
        String name = scanner.nextLine();
        return name.trim();
    }

    public static String getCustomerFirstname() {
        System.out.print("Entrez le prenom du client: ");
        String firstname = scanner.nextLine();
        return firstname.trim();
    }

    public static String getCustomerGender() {
        System.out.print("Entrez le genre du client (Mr ou Mme): ");
        String gender = scanner.nextLine();
        if (!gender.equals("Mr") && !gender.equals("Mme")) {
            gender = "Mr";
        }
        return gender.trim();
    }

    public static String getCustomerAddress() {
        System.out.print("Entrez l'adresse du client: ");
        String address = scanner.nextLine();
        return address.trim();
    }

    public static String getCustomerPhone() {
        System.out.print("Entrez le numero de telephone du client: ");
        String phone = scanner.nextLine();
        return phone.trim();
    }

    public static String getCustomerEmail() {
        System.out.print("Entrez l'email du client: ");
        String email = scanner.nextLine();
        return email.trim();
    }

    public static String getCustomerPassword() {
        System.out.print("Entrez le mot de passe du client: ");
        String password = scanner.nextLine();
        return password.trim();
    }

    public static String getCustomerType() {
        System.out.print("Entrez le type du client (Particulier ou Entreprise): ");
        String type = scanner.nextLine();
        if (!type.equals("Particulier") && !type.equals("Entreprise")) {
            type = "Particulier";
        }
        return type.trim();
    }

    public static String convertDate(String date) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateFromBD = dbDateFormat.parse(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy 'à' HH:mm");
            return dateFormat.format(dateFromBD);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }
}