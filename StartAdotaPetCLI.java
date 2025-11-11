import fileProcess.FileReaderService;
import menu.HomeMenu;
import menu.StartMenu;
import utils.LoadingAnimation;
import utils.Style;

import java.io.BufferedReader;
import java.io.FileReader;
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

        LoadingAnimation.bar(1000);

        // Display home menu
        HomeMenu homeMenu = new HomeMenu();
        homeMenu.display();

        // Read questions from file
        //FileReaderService fileReaderService = new FileReaderService();
        //fileReaderService.readAllQuestionsFile(formsPath);
        //fileReaderService.showIndexQuestion(fileReaderService.questionsList, 0);
        LoadingAnimation.dots(600);
        int indexHomeMenu;
        do {
            System.out.print(Style.italic("Please select an option >>> "));
            indexHomeMenu = sc.nextInt();
            while (indexHomeMenu < 1 || indexHomeMenu > 5) {
                System.out.println(Style.msgError("Invalid option..."));
                System.out.print(Style.italic("Please select an option [1 - 5] >>> "));
                indexHomeMenu = sc.nextInt();

            }
            switch (indexHomeMenu) {
                case 1:
                    System.out.println(Style.msgOk("Register a new pet"));



                    break;
                case 2:
                    System.out.println("Delete a registered pet");
                    break;
                case 3:
                    System.out.println("List all registered pets");
                    break;
                case 4:
                    System.out.println("List pets by criteria");
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
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
