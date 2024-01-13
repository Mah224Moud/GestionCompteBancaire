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

    /**
     * Generates a hashed password using the BCrypt algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check if the given password matches the given hashed password.
     *
     * @param password       the plain text password to be checked
     * @param hashedPassword the hashed password to compare against
     * @return true if the password matches the hashed password, false otherwise
     */
    public static boolean isValidPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    /**
     * Validates if the given string is a valid email address.
     *
     * @param email the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    /**
     * Generates a random string of the specified length.
     *
     * @param length the length of the random string
     * @return the generated random string
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Formats the given amount as a string with a currency symbol.
     *
     * @param amount the amount to be formatted
     * @return the formatted amount with a currency symbol
     */
    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,###.00", new DecimalFormatSymbols(Locale.US));
        return df.format(amount) + "€";
    }

    /**
     * Prints the menu options for the customer.
     *
     */
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

    /**
     * Prints the menu options for the banker application.
     */
    public static void menuBanker() {
        System.out.println(
                "\n" +
                        "*********************************************\n" +
                        "******************** MENU *******************\n" +
                        "*********************************************");
        System.out.println("1. Voir le solde d'un client");
        System.out.println("2. Ajouter un client");
        System.out.println("3. Quitter");
        System.out.println(
                "*********************************************\n" +
                        "*********************************************\n");
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return the name of the customer as a string
     */
    public static String getCustomerName() {
        System.out.print("Entrez le nom du client: ");
        String name = scanner.nextLine();
        return name.trim();
    }

    /**
     * A method to get the customer's first name.
     *
     * @return the customer's first name as a trimmed string
     */
    public static String getCustomerFirstname() {
        System.out.print("Entrez le prenom du client: ");
        String firstname = scanner.nextLine();
        return firstname.trim();
    }

    /**
     * Retrieves the gender of the customer.
     *
     * @return the gender of the customer as a string
     */
    public static String getCustomerGender() {
        System.out.print("Entrez le genre du client (Mr ou Mme): ");
        String gender = scanner.nextLine();
        if (!gender.equals("Mr") && !gender.equals("Mme")) {
            gender = "Mr";
        }
        return gender.trim();
    }

    /**
     * Retrieves the customer's address.
     *
     * @return the customer's address as a string
     */
    public static String getCustomerAddress() {
        System.out.print("Entrez l'adresse du client: ");
        String address = scanner.nextLine();
        return address.trim();
    }

    /**
     * Get the phone number of the customer.
     *
     * @return The phone number of the customer.
     */
    public static String getCustomerPhone() {
        System.out.print("Entrez le numero de telephone du client: ");
        String phone = scanner.nextLine();
        return phone.trim();
    }

    /**
     * Prompt the user to enter the customer's email and return the trimmed email
     * string.
     *
     * @return The trimmed email string entered by the user.
     */
    public static String getCustomerEmail() {
        System.out.print("Entrez l'email du client: ");
        String email = scanner.nextLine();
        return email.trim();
    }

    /**
     * Retrieves the customer password.
     *
     * @return the customer password
     */
    public static String getCustomerPassword() {
        System.out.print("Entrez le mot de passe du client: ");
        String password = scanner.nextLine();
        return password.trim();
    }

    /**
     * Retrieves the type of the customer.
     *
     * @return the type of the customer (Particulier or Entreprise)
     */
    public static String getCustomerType() {
        System.out.print("Entrez le type du client (Particulier ou Entreprise): ");
        String type = scanner.nextLine();
        if (!type.equals("Particulier") && !type.equals("Entreprise")) {
            type = "Particulier";
        }
        return type.trim();
    }

    /**
     * Converts a date string from the format 'yyyy-MM-dd HH:mm:ss' to the format
     * 'dd MMM yyyy 'à' HH:mm'.
     *
     * @param date the date string to be converted
     * @return the converted date string in the format 'dd MMM yyyy 'à' HH:mm'
     */
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