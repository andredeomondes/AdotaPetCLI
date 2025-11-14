import fileProcess.FileReaderService;
import fileProcess.FileWriterService;
import pet.Pet;
import pet.PetAddress;
import pet.enums.PetType;
import pet.enums.PetSex;
import menu.HomeMenu;
import menu.StartMenu;
import utils.LoadingAnimation;
import utils.Style;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Entry point for the AdotaPet CLI system.
 *
 * <p>This class is responsible for controlling the user flow, displaying menus,
 * loading form questions, validating inputs, and orchestrating the pet registration process.
 *
 * <p>The CLI supports:
 * <ul>
 *     <li>Interactive menu navigation</li>
 *     <li>Pet registration with field validation</li>
 *     <li>Address collection and optional fields</li>
 *     <li>Graceful handling of invalid inputs</li>
 * </ul>
 *
 * <p>All constants defined in this class represent application-wide settings,
 * such as default paths and "NOT INFORMED" placeholders for missing user input.
 */
public class StartAdotaPetCLI {

    /**
     * Placeholder used when the user does not provide optional information.
     */
    public static final String NA = "NOT INFORMED";

    /**
     * Path to the file containing the registration form questions.
     */
    public static final String formsPath = "forms.txt";

    /**
     * Directory where registered pets will be stored.
     */
    public static final String directoryPath = "registeredPets";

    /**
     * Main execution method for the CLI application.
     *
     * @param args program arguments (not used in this CLI implementation)
     */
    public static void main(String[] args) {

        // Ensures consistent locale formatting for numbers, dates, etc.
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        try {
            /* ----------------------------- INITIAL SCREENS ----------------------------- */

            // Display initial welcome screen
            StartMenu startMenu = new StartMenu();
            startMenu.display();
            LoadingAnimation.bar(300);

            // Display home menu with main options
            HomeMenu homeMenu = new HomeMenu();
            homeMenu.display();

            /* ----------------------------- LOAD FORM QUESTIONS ----------------------------- */

            FileReaderService fileReaderService = new FileReaderService();
            fileReaderService.readAllQuestionsFile(formsPath);
            LoadingAnimation.dots(300);

            int indexHomeMenu;

            /* ----------------------------- MAIN MENU LOOP ----------------------------- */
            do {

                System.out.print(Style.italic("Please select an option >>> "));

                // Validate numeric input before reading
                while (!sc.hasNextInt()) {
                    System.out.println(Style.msgError("Invalid option..."));
                    System.out.print(Style.italic("Please select a valid number [1 - 5] >>> "));
                    sc.nextLine();
                }

                indexHomeMenu = sc.nextInt();
                sc.nextLine();

                // Ensure that option is within allowed range
                while (indexHomeMenu < 1 || indexHomeMenu > 5) {
                    System.out.println(Style.msgError("Invalid option..."));
                    System.out.print(Style.italic("Please select an option [1 - 5] >>> "));
                    indexHomeMenu = sc.nextInt();
                    sc.nextLine();
                }

                /* ----------------------------- MENU OPTIONS ----------------------------- */

                switch (indexHomeMenu) {

                    /* ----------------------------- REGISTER PET ----------------------------- */
                    case 1:
                        System.out.println("\n" + Style.msgOk("Register new pet  [ Selected ]"));

                        // Default values for the new Pet
                        String name = NA, breed = NA, response;
                        PetType petType = null;
                        PetSex petSex = null;
                        PetAddress petAddress = new PetAddress(null, null, null);
                        Double weight = null, age = null;

                        // Loop through each form question
                        for (int indexQuestion = 0;
                             indexQuestion < fileReaderService.questionsList.size();
                             indexQuestion++) {

                            fileReaderService.showIndexQuestion(fileReaderService.questionsList, indexQuestion);

                            /* ----------------------------- FORM INPUT PROCESSING ----------------------------- */
                            switch (indexQuestion) {

                                /* ------------------------ QUESTION 01 - PET NAME ------------------------ */
                                case 0:
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here >>> "));
                                        response = sc.nextLine().trim();
                                        String fields[] = response.split(" ");

                                        if (response.isEmpty()) {
                                            name = NA;
                                            break;
                                        } else if (fields.length < 2) {
                                            System.out.println(Style.msgError(" Type NAME AND LASTNAME (ex.: Michael Jackson)"));
                                        } else if (!response.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$")) {
                                            System.out.println(Style.msgError(" Use only letters (a–z, A–Z) and spaces."));
                                        } else {
                                            name = response;
                                            System.out.println(Style.msgOk("Name: OK"));
                                            break;
                                        }
                                    } while (true);
                                    break;

                                /* ------------------------ QUESTION 02 - PET TYPE ------------------------ */
                                case 1:
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here [1-DOG / 2-CAT] >>> "));
                                        response = sc.nextLine().trim();

                                        if (!response.matches("[0-9]+")) {
                                            System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                                            continue;
                                        }

                                        int type = Integer.parseInt(response);

                                        if (type == 1) {
                                            petType = PetType.DOG;
                                            break;
                                        } else if (type == 2) {
                                            petType = PetType.CAT;
                                            break;
                                        } else {
                                            System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                                        }

                                    } while (true);

                                    System.out.println();
                                    System.out.println(Style.msgOk("Pet type OK!"));
                                    break;

                                /* ------------------------ QUESTION 03 - PET SEX ------------------------ */
                                case 2:
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here [1-MALE / 2-FEMALE] >>> "));
                                        response = sc.nextLine().trim();

                                        if (!response.matches("[0-9]+")) {
                                            System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                                            continue;
                                        }

                                        int type = Integer.parseInt(response);

                                        if (type == 1) {
                                            petSex = PetSex.MALE;
                                            break;
                                        } else if (type == 2) {
                                            petSex = PetSex.FEMALE;
                                            break;
                                        } else {
                                            System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                                        }

                                    } while (true);

                                    System.out.println();
                                    System.out.println(Style.msgOk("Pet sex OK!"));
                                    break;

                                /* ------------------------ QUESTION 04 - ADDRESS ------------------------ */
                                case 3:
                                    // City ---------------------------------------------------------------
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here [ CITY ] >>> "));
                                        response = sc.nextLine().trim();

                                        if (response.isEmpty()) {
                                            System.out.println(Style.msgError("[CITY] Cannot be empty..."));
                                        } else {
                                            petAddress.setCity(response);
                                            System.out.println(Style.msgOk("CITY OK!"));
                                            break;
                                        }
                                    } while (true);

                                    // Street ---------------------------------------------------------------
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here [ STREET ] >>> "));
                                        response = sc.nextLine();

                                        if (response.isEmpty()) {
                                            System.out.println(Style.msgError("[STREET] Cannot be empty..."));
                                        } else {
                                            petAddress.setStreet(response);
                                            System.out.println(Style.msgOk("Street OK!"));
                                            break;
                                        }
                                    } while (true);

                                    // Number ---------------------------------------------------------------
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here [ NUMBER ] >>> "));
                                        response = sc.nextLine().trim();

                                        if (response.isEmpty()) {
                                            petAddress.setNumber(null);
                                            System.out.println("\n" + Style.msgOk("NUMBER OK!"));
                                            break;
                                        }

                                        if (!response.matches("[0-9]+")) {
                                            System.out.println("\n" + Style.msgError("Invalid input, just numbers is accepted..."));
                                        } else {
                                            int number = Integer.parseInt(response);
                                            petAddress.setNumber(number);
                                            System.out.println("\n" + Style.msgOk(" NUMBER OK! "));
                                            break;
                                        }
                                    } while (true);

                                    break;

                                // FORMS QUESTION 05 -------------------------------------------------
// AGE -------------------------------------------------
                                case 4:
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here >>> "));
                                        response = sc.nextLine().trim();

                                        if (response.isEmpty()) {
                                            age = null;
                                            System.out.println(Style.msgOk("AGE OK!"));
                                            break;
                                        }

                                        if (!response.matches("[0-9]+")) {
                                            System.out.println(Style.msgError("Invalid input, just numbers are accepted..."));
                                            continue;
                                        }

                                        int input = Integer.parseInt(response);

                                        if (input > 20) {
                                            throw new IllegalArgumentException("Age cannot be more than 20 years!");
                                        } else if (input < 1) {
                                            // Convert months to years
                                            age = input / 12.0;
                                            System.out.println(Style.msgOk("AGE OK! Converted from months to years: " + String.format("%.2f", age)));
                                        } else {
                                            age = (double) input;
                                            System.out.println(Style.msgOk("AGE OK!"));
                                        }

                                    } while (true);
                                    break;

                                /* ------------------------ QUESTION 06 - WEIGHT ------------------------ */
                                case 5:
                                    do {
                                        System.out.print(Style.msgInfo(" // Type here >>> "));
                                        response = sc.nextLine().trim();

                                        if (response.isEmpty()) {
                                            weight = null;
                                            System.out.println(Style.msgOk("WEIGHT OK!"));
                                            break;
                                        }

                                        if (!response.matches("^[0-9]+([.,][0-9]+)?$+")) {
                                            System.out.println("\n" + Style.msgError("Invalid input, just numbers is accepted..."));
                                            continue;
                                        }

                                        String normalized = response.replace(",", ".");

                                        double w = Double.parseDouble(normalized);

                                        if (w < 0.5 || w > 60.0) {
                                            System.out.println(Style.msgError("Invalid weight. Must be between 0.5kg and 60kg."));
                                            continue;
                                        }
                                        weight = w;
                                        System.out.println(Style.msgOk("WEIGHT OK!"));
                                    } while (true);
                                    break;

                                /* ------------------------ QUESTION 07 - BREED ------------------------ */
                                case 6:
                                    System.out.print(Style.msgInfo(" // Type here >>> "));
                                    response = sc.nextLine().trim();

                                    if (response.isEmpty()) {
                                        breed = NA;
                                        System.out.println("\n" + Style.msgOk("BREED OK "));
                                    } else {
                                        breed = response;
                                    }
                                    break;

                                default:
                                    break;
                            }
                        }

                        // Create pet object after collecting all fields
                        Pet pet = new Pet(name, petType, petSex, petAddress, age, weight, breed);
                        FileWriterService writer = new FileWriterService(directoryPath);
                        writer.savePet(pet);

                        System.out.println(Style.msgOk("Pet registration completed (temporary message)."));
                        break;

                    /* ----------------------------- OTHER OPTIONS ----------------------------- */

                    case 2:
                        System.out.println("Option 2 selected.");
                        break;

                    case 3:
                        System.out.println("Option 3 selected.");
                        break;

                    case 4:
                        System.out.println("Option 4 selected.");
                        break;

                    case 5:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println(Style.msgError("Invalid option..."));
                }

            } while (indexHomeMenu != 5);

        } catch (RuntimeException e) {
            System.out.println(Style.msgError("Error: " + e.getMessage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Close Scanner resource
        sc.close();
    }
}
