import fileProcess.FileReaderService;
import menu.HomeMenu;
import menu.StartMenu;
import utils.LoadingAnimation;
import utils.Style;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class StartAdotaPetCLI {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Define file paths
        final String formsPath = "forms.txt";
        final String directoryPath = "registeredPets";

        // Welcome message with loading animations
        StartMenu startMenu = new StartMenu();
        startMenu.display();

        LoadingAnimation.bar(300);

        // Display home menu
        HomeMenu homeMenu = new HomeMenu();
        homeMenu.display();

        // Read questions from file
        FileReaderService fileReaderService = new FileReaderService();
        fileReaderService.readAllQuestionsFile(formsPath);
        LoadingAnimation.dots(300);

        //
        int indexHomeMenu;
        do {
            System.out.print(Style.italic("Please select an option >>> "));
            indexHomeMenu = sc.nextInt();
            sc.nextLine();
            while (indexHomeMenu < 1 || indexHomeMenu > 5) {
                System.out.println(Style.msgError("Invalid option..."));
                System.out.print(Style.italic("Please select an option [1 - 5] >>> "));
                indexHomeMenu = sc.nextInt();
                sc.nextLine();

            }
            try {
                switch (indexHomeMenu) {
                    case 1:
                        String name;
                        String response;

                        System.out.println("\n" + Style.msgOk("Register new pet  [ Selected ]"));
                        for (int indexQuestion = 0; indexQuestion < fileReaderService.questionsList.size(); indexQuestion++) {
                            fileReaderService.showIndexQuestion(fileReaderService.questionsList, indexQuestion);
                            switch (indexQuestion) {
                                case 0:
                                    boolean validation;
                                    do {
                                        validation = true;

                                        System.out.print(Style.msgInfo(" // Type here >>> "));
                                        response = sc.nextLine();

                                        // Validation name & lastname
                                        String fields[] = response.split(" ");

                                        if (fields.length < 2) {
                                            System.out.println(Style.msgError(" Type a NAME AND LASTNAME (ex.: Michal Jackson)"));
                                            validation = false;
                                        }

                                        if (!response.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$")) {
                                            System.out.println(Style.msgError(" Use only letters (a–z, A–Z) and spaces."));
                                            validation = false;
                                        }


                                    } while (!validation);

                                    name = response;
                                    System.out.println(Style.msgOk("Name: OK"));

                                    break;
                                case 1:

                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.out.println(Style.msgError("Invalid option..."));
                                    break;
                            }

                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (
                    RuntimeException e) {
                System.out.println(Style.msgError("Error: " + e.getMessage()));
            }
        } while (indexHomeMenu != 5);
        // System.out.println("Loading dots:");
        // LoadingAnimation.dots(2500);

        // System.out.println("Classic spinner:");
        // LoadingAnimation.spinner(2500);

        // System.out.println("Smooth spinner:");
        // LoadingAnimation.smoothSpinner(2500);


        // System.out.println(Style.msgInfo("Carregando dados do sistema..."));
        // System.out.println(Style.msgSucesso("Pet cadastrado com sucesso!"));
        // System.out.println(Style.msgAviso("Campo 'idade' não preenchido."));
        // System.out.println(Style.msgErro("Falha ao salvar no arquivo."));
        sc.close();
    }
}