package pet; // Importante: Garante o acesso ao PetController/PetService

import fileProcess.FileReaderService;
import fileProcess.FileWriterService;
import menu.HomeMenu;
import menu.StartMenu;
import utils.LoadingAnimation;
import utils.Style;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class StartAdotaPetCLI {

    public static final String formsPath = "forms.txt";
    public static final String directoryPath = "registeredPets";


    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        FileReaderService fileReaderService = new FileReaderService();

        try {
            /* ----------------------------- INITIAL SCREENS & LOAD QUESTIONS ----------------------------- */

            StartMenu startMenu = new StartMenu();
            startMenu.display();
            LoadingAnimation.bar(300);

            HomeMenu homeMenu = new HomeMenu();
            homeMenu.display();

            fileReaderService.readAllQuestionsFile(formsPath);
            LoadingAnimation.dots(300);

            /* ----------------------------- INJEÇÃO DE DEPENDÊNCIA (DI) ----------------------------- */

            // 1. Service de Persistência
            FileWriterService fileWriterService = new FileWriterService(directoryPath);

            // 2. Service de Negócio (injeta o FileWriterService)
            PetService petService = new PetService(fileWriterService);

            // 3. Controller (injeta PetService, FileReaderService e Scanner)
            PetController petController = new PetController(petService, fileReaderService, sc);

            int indexHomeMenu;

            /* ----------------------------- MAIN MENU LOOP ----------------------------- */
            do {

                System.out.print(Style.italic("Please select an option >>> "));

                // Validação de input numérico
                while (!sc.hasNextInt()) {
                    System.out.println(Style.msgError("Invalid option..."));
                    System.out.print(Style.italic("Please select a valid number [1 - 6] >>> "));
                    sc.nextLine();
                }

                indexHomeMenu = sc.nextInt();
                sc.nextLine();

                // Validação do range
                while (indexHomeMenu < 1 || indexHomeMenu > 6) {
                    System.out.println(Style.msgError("Invalid option..."));
                    System.out.print(Style.italic("Please select an option [1 - 6] >>> "));
                    indexHomeMenu = sc.nextInt();
                    sc.nextLine();
                }

                /* ----------------------------- MENU OPTIONS ----------------------------- */

                switch (indexHomeMenu) {

                    /* ----------------------------- REGISTER PET ----------------------------- */
                    case 1:
                        petController.handleRegisterPet();
                        break;

                    /* ----------------------------- OTHER OPTIONS ----------------------------- */

                    case 2:
                        System.out.println("\n" + Style.msgOk("Delete registered pet [ Selected ] (Implementar lógica aqui ou em PetController)."));
                        break;

                    case 3:
                        System.out.println("Option 3 selected. (Implementar lógica aqui ou em PetController).");
                        break;

                    case 4:
                        System.out.println("Option 4 selected. (Implementar lógica aqui ou em PetController).");
                        break;

                    case 5:
                        System.out.println("Option 5 selected. (Implementar lógica aqui ou em PetController).");
                        break;

                    case 6:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        // Não deve ser alcançado
                        System.out.println(Style.msgError("Internal error: Invalid option index."));
                }

            } while (indexHomeMenu != 6);

        } catch (RuntimeException e) {
            System.out.println(Style.msgError("Fatal Error: " + e.getMessage()));
        } finally {
            sc.close();
        }
    }
}